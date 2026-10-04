package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.knowledge.AiMode
import com.example.data.knowledge.KnowledgeSourceItem
import com.example.data.knowledge.KnowledgeSourceRegistry
import com.example.data.knowledge.OfflineKnowledgeEngine
import com.example.data.local.AppDatabase
import com.example.data.local.DefaultWordsCatalog
import com.example.data.local.WordEntity
import com.example.data.network.LexicalLanguageData
import com.example.data.network.LexicalLanguageService
import com.example.data.network.OpenRouterService
import com.example.data.network.WikipediaService
import com.example.data.network.WikipediaSummary
import com.example.data.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val wordDao = db.wordDao()
    private val prefsRepo = UserPreferencesRepository(application)
    private val openRouterService = OpenRouterService()
    private val wikipediaService = WikipediaService()
    private val lexicalService = LexicalLanguageService()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isSearchVisible = MutableStateFlow(false)
    val isSearchVisible: StateFlow<Boolean> = _isSearchVisible.asStateFlow()

    private val _isTopBarCollapsed = MutableStateFlow(false)
    val isTopBarCollapsed: StateFlow<Boolean> = _isTopBarCollapsed.asStateFlow()

    private val _selectedLetter = MutableStateFlow<String?>(null)
    val selectedLetter: StateFlow<String?> = _selectedLetter.asStateFlow()

    private val _selectedFilter = MutableStateFlow("ALL")
    val selectedFilter: StateFlow<String> = _selectedFilter.asStateFlow()

    private val _selectedWord = MutableStateFlow<WordEntity?>(null)
    val selectedWord: StateFlow<WordEntity?> = _selectedWord.asStateFlow()

    // Multi-mode AI State
    private val _activeAiMode = MutableStateFlow(AiMode.DEEP_ANALOGY)
    val activeAiMode: StateFlow<AiMode> = _activeAiMode.asStateFlow()

    private val _customAiQuestion = MutableStateFlow("")
    val customAiQuestion: StateFlow<String> = _customAiQuestion.asStateFlow()

    private val _openRouterLoading = MutableStateFlow(false)
    val openRouterLoading: StateFlow<Boolean> = _openRouterLoading.asStateFlow()

    private val _openRouterResult = MutableStateFlow<String?>(null)
    val openRouterResult: StateFlow<String?> = _openRouterResult.asStateFlow()

    private val _openRouterError = MutableStateFlow<String?>(null)
    val openRouterError: StateFlow<String?> = _openRouterError.asStateFlow()

    private val _isAiKeptSavedSuccess = MutableStateFlow(false)
    val isAiKeptSavedSuccess: StateFlow<Boolean> = _isAiKeptSavedSuccess.asStateFlow()

    // Wikipedia & Knowledge Sources State
    private val _wikipediaLoading = MutableStateFlow(false)
    val wikipediaLoading: StateFlow<Boolean> = _wikipediaLoading.asStateFlow()

    private val _wikipediaSummary = MutableStateFlow<WikipediaSummary?>(null)
    val wikipediaSummary: StateFlow<WikipediaSummary?> = _wikipediaSummary.asStateFlow()

    private val _knowledgeSources = MutableStateFlow<List<KnowledgeSourceItem>>(emptyList())
    val knowledgeSources: StateFlow<List<KnowledgeSourceItem>> = _knowledgeSources.asStateFlow()

    // Lexical Language, Pronunciation & Grammar State
    private val _lexicalLoading = MutableStateFlow(false)
    val lexicalLoading: StateFlow<Boolean> = _lexicalLoading.asStateFlow()

    private val _lexicalData = MutableStateFlow<LexicalLanguageData?>(null)
    val lexicalData: StateFlow<LexicalLanguageData?> = _lexicalData.asStateFlow()

    // Dialog States
    private val _showAddWordDialog = MutableStateFlow(false)
    val showAddWordDialog: StateFlow<Boolean> = _showAddWordDialog.asStateFlow()

    private val _showAiAddDialog = MutableStateFlow(false)
    val showAiAddDialog: StateFlow<Boolean> = _showAiAddDialog.asStateFlow()

    private val _aiAddInitialTerm = MutableStateFlow("")
    val aiAddInitialTerm: StateFlow<String> = _aiAddInitialTerm.asStateFlow()

    private val _isAiGeneratingWord = MutableStateFlow(false)
    val isAiGeneratingWord: StateFlow<Boolean> = _isAiGeneratingWord.asStateFlow()

    private val _aiWordError = MutableStateFlow<String?>(null)
    val aiWordError: StateFlow<String?> = _aiWordError.asStateFlow()

    private val _showSettingsDialog = MutableStateFlow(false)
    val showSettingsDialog: StateFlow<Boolean> = _showSettingsDialog.asStateFlow()

    val themeMode: StateFlow<String> = prefsRepo.themeModeFlow.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        "SYSTEM"
    )

    val openRouterApiKey: StateFlow<String> = prefsRepo.openRouterApiKeyFlow.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        ""
    )

    val openRouterModel: StateFlow<String> = prefsRepo.openRouterModelFlow.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        "openrouter/free"
    )

    val autoKeepAiInApp: StateFlow<Boolean> = prefsRepo.autoKeepAiInAppFlow.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        true
    )

    // Master stream of all words from Room database
    private val rawWordsFlow = wordDao.getAllWordsFlow()

    val filteredWords: StateFlow<List<WordEntity>> = combine(
        rawWordsFlow,
        _searchQuery,
        _selectedLetter,
        _selectedFilter
    ) { allWords, query, letter, filter ->
        var list = allWords

        // 1. Filter by section/filter
        when (filter) {
            "IMPORTANT" -> list = list.filter { it.isImportant }
            "AI_KEPT" -> list = list.filter { it.hasAiNotes || it.savedAiNotes.isNotBlank() }
            "LEVEL_BASIC" -> list = list.filter { it.level == WordEntity.LEVEL_BASIC }
            "LEVEL_INTERMEDIATE" -> list = list.filter { it.level == WordEntity.LEVEL_INTERMEDIATE }
            "LEVEL_ADVANCED" -> list = list.filter { it.level == WordEntity.LEVEL_ADVANCED }
            "NETWORKING" -> list = list.filter { it.part.contains("Networking", ignoreCase = true) || it.category.equals("Networking", ignoreCase = true) }
            "HARNESSES" -> list = list.filter { it.part.contains("Harness", ignoreCase = true) || it.category.contains("Harness", ignoreCase = true) || it.category.contains("Framework", ignoreCase = true) || it.category.contains("MCP", ignoreCase = true) || it.category.contains("Vector", ignoreCase = true) }
            "MASTER_LIST" -> list = list.filter { it.part.contains("Master", ignoreCase = true) }
            "BOOKMARKED" -> list = list.filter { it.isBookmarked }
            "CUSTOM" -> list = list.filter { it.isCustom }
        }

        // 2. Filter by Alphabet letter
        if (!letter.isNullOrBlank()) {
            list = list.filter { it.term.trim().startsWith(letter, ignoreCase = true) }
        }

        // 3. Filter by Search Query
        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            list = list.filter { word ->
                word.term.lowercase().contains(q) ||
                word.humanMeaning.lowercase().contains(q) ||
                word.keyPoints.lowercase().contains(q) ||
                word.category.lowercase().contains(q) ||
                word.practicalUses.lowercase().contains(q) ||
                word.savedAiNotes.lowercase().contains(q)
            }
        }

        // Strict alphabetical order
        list.sortedBy { it.term.lowercase() }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        initializeDatabaseIfNeeded()
    }

    private fun initializeDatabaseIfNeeded() {
        viewModelScope.launch {
            val count = wordDao.getCount()
            if (count == 0) {
                val catalog = DefaultWordsCatalog.getAllCatalogWords()
                wordDao.insertWords(catalog)
            }
            prefsRepo.setDbInitialized(true)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleSearchVisible() {
        _isSearchVisible.value = !_isSearchVisible.value
        if (!_isSearchVisible.value) {
            _searchQuery.value = ""
        }
    }

    fun closeSearch() {
        _isSearchVisible.value = false
        _searchQuery.value = ""
    }

    fun toggleTopBarCollapsed() {
        _isTopBarCollapsed.value = !_isTopBarCollapsed.value
    }

    fun selectLetter(letter: String?) {
        if (_selectedLetter.value == letter) {
            _selectedLetter.value = null // toggle off
        } else {
            _selectedLetter.value = letter
        }
    }

    fun selectFilter(filter: String) {
        _selectedFilter.value = filter
    }

    fun selectWord(word: WordEntity?) {
        _selectedWord.value = word
        _openRouterResult.value = null
        _openRouterError.value = null
        _isAiKeptSavedSuccess.value = false
        _wikipediaSummary.value = null
        _lexicalData.value = null

        if (word != null) {
            _knowledgeSources.value = KnowledgeSourceRegistry.getKnowledgeSourcesForWord(word)
            // Pre-fetch Wikipedia summary in background for richer knowledge
            fetchWikipediaKnowledge(word)
            // Pre-fetch lexical linguistic data for phonetics & grammar
            fetchLexicalData(word)
        } else {
            _knowledgeSources.value = emptyList()
        }
    }

    fun selectRandomWord() {
        val currentList = filteredWords.value
        if (currentList.isNotEmpty()) {
            selectWord(currentList.random())
        }
    }

    fun selectNextWord() {
        val currentList = filteredWords.value
        val current = _selectedWord.value ?: return
        val idx = currentList.indexOfFirst { it.id == current.id }
        if (idx != -1 && idx < currentList.size - 1) {
            selectWord(currentList[idx + 1])
        } else if (currentList.isNotEmpty()) {
            selectWord(currentList.first())
        }
    }

    fun selectPreviousWord() {
        val currentList = filteredWords.value
        val current = _selectedWord.value ?: return
        val idx = currentList.indexOfFirst { it.id == current.id }
        if (idx > 0) {
            selectWord(currentList[idx - 1])
        } else if (currentList.isNotEmpty()) {
            selectWord(currentList.last())
        }
    }

    fun setActiveAiMode(mode: AiMode) {
        _activeAiMode.value = mode
    }

    fun setCustomAiQuestion(question: String) {
        _customAiQuestion.value = question
    }

    fun fetchLexicalData(word: WordEntity) {
        viewModelScope.launch {
            _lexicalLoading.value = true
            try {
                val data = lexicalService.resolveLexicalLanguageData(word.term)
                _lexicalData.value = data
            } catch (_: Exception) {
            } finally {
                _lexicalLoading.value = false
            }
        }
    }

    fun fetchWikipediaKnowledge(word: WordEntity) {
        viewModelScope.launch {
            _wikipediaLoading.value = true
            val result = wikipediaService.fetchSummary(word.term)
            result.fold(
                onSuccess = { summary ->
                    _wikipediaSummary.value = summary
                    _wikipediaLoading.value = false
                },
                onFailure = {
                    _wikipediaLoading.value = false
                }
            )
        }
    }

    fun toggleBookmark(word: WordEntity) {
        viewModelScope.launch {
            val newStatus = !word.isBookmarked
            wordDao.updateBookmark(word.id, newStatus)
            if (_selectedWord.value?.id == word.id) {
                _selectedWord.value = word.copy(isBookmarked = newStatus)
            }
        }
    }

    fun toggleImportance(word: WordEntity) {
        viewModelScope.launch {
            val newStatus = !word.isImportant
            wordDao.updateImportance(word.id, newStatus)
            if (_selectedWord.value?.id == word.id) {
                _selectedWord.value = word.copy(isImportant = newStatus)
            }
        }
    }

    // AI "Keep in App" Feature: permanently retains AI explanations/code on this word in Room DB
    fun keepAiOutputInApp(word: WordEntity, aiContent: String) {
        val trimmed = aiContent.trim()
        if (trimmed.isBlank()) return

        viewModelScope.launch {
            val currentNotes = word.savedAiNotes.trim()
            val updatedNotes = if (currentNotes.isBlank()) {
                trimmed
            } else if (!currentNotes.contains(trimmed)) {
                "$currentNotes\n\n---\n\n$trimmed"
            } else {
                currentNotes
            }

            wordDao.updateAiNotes(
                id = word.id,
                notes = updatedNotes,
                hasNotes = true,
                timestamp = System.currentTimeMillis()
            )

            val updatedWord = word.copy(
                savedAiNotes = updatedNotes,
                hasAiNotes = true,
                aiNotesTimestamp = System.currentTimeMillis()
            )

            if (_selectedWord.value?.id == word.id) {
                _selectedWord.value = updatedWord
            }
            _isAiKeptSavedSuccess.value = true
        }
    }

    fun applyAiAsMainDefinition(word: WordEntity, aiContent: String) {
        val trimmed = aiContent.trim()
        if (trimmed.isBlank()) return

        viewModelScope.launch {
            // Take the leading sentence/paragraph as the refreshed human meaning
            val firstPara = trimmed.lines().firstOrNull { it.isNotBlank() && !it.startsWith("#") && !it.startsWith("💡") } ?: trimmed.take(250)
            val cleanMeaning = firstPara.removePrefix("•").trim()

            wordDao.updateHumanMeaning(word.id, cleanMeaning)
            val updatedWord = word.copy(humanMeaning = cleanMeaning)

            if (_selectedWord.value?.id == word.id) {
                _selectedWord.value = updatedWord
            }
            _isAiKeptSavedSuccess.value = true
        }
    }

    fun saveCustomAiNotes(word: WordEntity, notes: String) {
        val trimmed = notes.trim()
        viewModelScope.launch {
            wordDao.updateAiNotes(
                id = word.id,
                notes = trimmed,
                hasNotes = trimmed.isNotBlank(),
                timestamp = System.currentTimeMillis()
            )
            val updatedWord = word.copy(
                savedAiNotes = trimmed,
                hasAiNotes = trimmed.isNotBlank(),
                aiNotesTimestamp = System.currentTimeMillis()
            )
            if (_selectedWord.value?.id == word.id) {
                _selectedWord.value = updatedWord
            }
        }
    }

    fun clearAiNotesFromWord(word: WordEntity) {
        viewModelScope.launch {
            wordDao.updateAiNotes(
                id = word.id,
                notes = "",
                hasNotes = false,
                timestamp = 0L
            )
            val updatedWord = word.copy(
                savedAiNotes = "",
                hasAiNotes = false,
                aiNotesTimestamp = 0L
            )
            if (_selectedWord.value?.id == word.id) {
                _selectedWord.value = updatedWord
            }
            _isAiKeptSavedSuccess.value = false
        }
    }

    fun toggleAutoKeepAi(enabled: Boolean) {
        viewModelScope.launch {
            prefsRepo.setAutoKeepAiInApp(enabled)
        }
    }

    fun addCustomWord(
        term: String,
        category: String,
        humanMeaning: String,
        keyPoints: String,
        practicalUses: String,
        examples: String,
        referenceUrl: String,
        level: String = WordEntity.LEVEL_BASIC,
        isImportant: Boolean = false
    ) {
        viewModelScope.launch {
            val newWord = WordEntity(
                term = term.trim(),
                category = if (category.isBlank()) "General AI" else category.trim(),
                part = "Custom",
                humanMeaning = humanMeaning.trim(),
                keyPoints = keyPoints.trim(),
                practicalUses = practicalUses.trim(),
                examples = examples.trim(),
                referenceUrl = referenceUrl.trim(),
                level = level,
                isImportant = isImportant,
                isCustom = true,
                isBookmarked = false,
                savedAiNotes = "",
                hasAiNotes = false
            )
            val id = wordDao.insertWord(newWord)
            val insertedWord = newWord.copy(id = id)
            selectWord(insertedWord)
            _showAddWordDialog.value = false
        }
    }

    fun deleteWord(word: WordEntity) {
        viewModelScope.launch {
            wordDao.deleteWord(word)
            if (_selectedWord.value?.id == word.id) {
                selectWord(null)
            }
        }
    }

    fun openAddWordDialog() {
        _showAddWordDialog.value = true
    }

    fun closeAddWordDialog() {
        _showAddWordDialog.value = false
    }

    fun openAiAddDialog(term: String = "") {
        _aiAddInitialTerm.value = term
        _aiWordError.value = null
        _showAiAddDialog.value = true
    }

    fun closeAiAddDialog() {
        _showAiAddDialog.value = false
        _aiAddInitialTerm.value = ""
        _aiWordError.value = null
    }

    fun clearAiWordError() {
        _aiWordError.value = null
    }

    fun generateAndAddWordWithAi(
        term: String,
        onSuccess: ((WordEntity) -> Unit)? = null
    ) {
        val trimmed = term.trim()
        if (trimmed.isBlank()) {
            _aiWordError.value = "Please enter a word or phrase to add."
            return
        }

        val key = openRouterApiKey.value
        if (key.isBlank()) {
            _aiWordError.value = "OpenRouter API Key not set. Tap Settings to enter your free key from openrouter.ai/keys."
            _showSettingsDialog.value = true
            return
        }

        viewModelScope.launch {
            _isAiGeneratingWord.value = true
            _aiWordError.value = null

            // First check if Wikipedia has context to ground the definition
            val wikiSummary = try {
                wikipediaService.fetchSummary(trimmed).getOrNull()
            } catch (e: Exception) { null }

            val wikiContext = wikiSummary?.extract

            val result = openRouterService.generateWordDefinition(
                apiKey = key,
                model = openRouterModel.value,
                term = trimmed,
                wikipediaContext = wikiContext
            )

            result.fold(
                onSuccess = { generatedEntity ->
                    val insertedId = wordDao.insertWord(generatedEntity)
                    val fullWord = generatedEntity.copy(id = insertedId)
                    selectWord(fullWord)
                    _isAiGeneratingWord.value = false
                    _showAiAddDialog.value = false
                    _showAddWordDialog.value = false
                    onSuccess?.invoke(fullWord)
                },
                onFailure = { error ->
                    _aiWordError.value = error.message ?: "Failed to generate word with OpenRouter free model."
                    _isAiGeneratingWord.value = false
                }
            )
        }
    }

    fun generateWordForForm(
        term: String,
        onGenerated: (WordEntity) -> Unit,
        onError: (String) -> Unit
    ) {
        val trimmed = term.trim()
        if (trimmed.isBlank()) {
            onError("Please enter a term first.")
            return
        }

        val key = openRouterApiKey.value
        if (key.isBlank()) {
            _showSettingsDialog.value = true
            onError("Please enter your OpenRouter API key in Settings first.")
            return
        }

        viewModelScope.launch {
            _isAiGeneratingWord.value = true
            val result = openRouterService.generateWordDefinition(
                apiKey = key,
                model = openRouterModel.value,
                term = trimmed
            )
            _isAiGeneratingWord.value = false
            result.fold(
                onSuccess = { entity ->
                    onGenerated(entity)
                },
                onFailure = { error ->
                    onError(error.message ?: "Error generating word with free model.")
                }
            )
        }
    }

    fun openSettingsDialog() {
        _showSettingsDialog.value = true
    }

    fun closeSettingsDialog() {
        _showSettingsDialog.value = false
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val current = themeMode.value
            val next = when (current) {
                "DARK" -> "LIGHT"
                "LIGHT" -> "DARK"
                else -> "DARK"
            }
            prefsRepo.setThemeMode(next)
        }
    }

    fun saveSettings(apiKey: String, model: String) {
        viewModelScope.launch {
            prefsRepo.setOpenRouterApiKey(apiKey)
            prefsRepo.setOpenRouterModel(model)
            _showSettingsDialog.value = false
        }
    }

    fun askOpenRouter(word: WordEntity) {
        askAiWithMode(
            word = word,
            mode = _activeAiMode.value,
            customQuestion = _customAiQuestion.value
        )
    }

    fun askAiWithMode(
        word: WordEntity,
        mode: AiMode,
        customQuestion: String? = null,
        forceOfflineFallback: Boolean = false
    ) {
        _activeAiMode.value = mode
        _isAiKeptSavedSuccess.value = false

        val key = openRouterApiKey.value
        // If force offline or key is blank, immediately use the Smart Offline Knowledge Engine
        if (forceOfflineFallback || key.isBlank()) {
            val offlineOutput = OfflineKnowledgeEngine.generateOfflineKnowledge(word, mode, customQuestion)
            _openRouterResult.value = offlineOutput
            _openRouterError.value = null
            _openRouterLoading.value = false

            // Auto-keep in app if enabled
            if (autoKeepAiInApp.value) {
                keepAiOutputInApp(word, offlineOutput)
            }
            return
        }

        viewModelScope.launch {
            _openRouterLoading.value = true
            _openRouterError.value = null
            _openRouterResult.value = null

            val wikiContext = _wikipediaSummary.value?.extract

            val result = openRouterService.queryWordInsightsWithMode(
                apiKey = key,
                model = openRouterModel.value,
                term = word.term,
                existingMeaning = word.humanMeaning,
                mode = mode,
                customQuestion = customQuestion,
                wikipediaContext = wikiContext
            )

            result.fold(
                onSuccess = { content ->
                    _openRouterResult.value = content
                    _openRouterLoading.value = false

                    // If auto-keep is on, immediately keep in app!
                    if (autoKeepAiInApp.value) {
                        keepAiOutputInApp(word, content)
                    }
                },
                onFailure = { error ->
                    // Offer fallback seamlessly if network/key issues
                    _openRouterError.value = error.message ?: "Failed to query AI model."
                    _openRouterLoading.value = false
                }
            )
        }
    }

    fun clearOpenRouterOutput() {
        _openRouterResult.value = null
        _openRouterError.value = null
        _isAiKeptSavedSuccess.value = false
    }
}
