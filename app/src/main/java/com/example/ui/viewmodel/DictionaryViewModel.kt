package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.DefaultWordsCatalog
import com.example.data.local.WordEntity
import com.example.data.network.OpenRouterService
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isSearchVisible = MutableStateFlow(false)
    val isSearchVisible: StateFlow<Boolean> = _isSearchVisible.asStateFlow()

    private val _isTopBarCollapsed = MutableStateFlow(false)
    val isTopBarCollapsed: StateFlow<Boolean> = _isTopBarCollapsed.asStateFlow()

    private val _selectedLetter = MutableStateFlow<String?>(null)
    val selectedLetter: StateFlow<String?> = _selectedLetter.asStateFlow()

    private val _selectedFilter = MutableStateFlow("ALL") // "ALL", "NETWORKING", "HARNESSES", "MASTER_LIST", "BOOKMARKED", "CUSTOM"
    val selectedFilter: StateFlow<String> = _selectedFilter.asStateFlow()

    private val _selectedWord = MutableStateFlow<WordEntity?>(null)
    val selectedWord: StateFlow<WordEntity?> = _selectedWord.asStateFlow()

    private val _openRouterLoading = MutableStateFlow(false)
    val openRouterLoading: StateFlow<Boolean> = _openRouterLoading.asStateFlow()

    private val _openRouterResult = MutableStateFlow<String?>(null)
    val openRouterResult: StateFlow<String?> = _openRouterResult.asStateFlow()

    private val _openRouterError = MutableStateFlow<String?>(null)
    val openRouterError: StateFlow<String?> = _openRouterError.asStateFlow()

    private val _showAddWordDialog = MutableStateFlow(false)
    val showAddWordDialog: StateFlow<Boolean> = _showAddWordDialog.asStateFlow()

    private val _showAiAddDialog = MutableStateFlow(false)
    val showAiAddDialog: StateFlow<Boolean> = _showAiAddDialog.asStateFlow()

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
                word.practicalUses.lowercase().contains(q)
            }
        }

        // Strict alphabetical order
        list.sortedBy { it.term.lowercase() }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        initializeDatabaseIfNeeded()
    }

    private fun initializeDatabaseIfNeeded() {
        viewModelScope.launch {
            val count = wordDao.getCount()
            val isInit = prefsRepo.isDbInitializedFlow.first()
            if (count == 0 || !isInit) {
                val catalog = DefaultWordsCatalog.getAllCatalogWords()
                wordDao.insertWords(catalog)
                prefsRepo.setDbInitialized(true)
            }
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

    fun addCustomWord(
        term: String,
        category: String,
        humanMeaning: String,
        keyPoints: String,
        practicalUses: String,
        examples: String,
        referenceUrl: String
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
                isCustom = true,
                isBookmarked = false
            )
            val id = wordDao.insertWord(newWord)
            _selectedWord.value = newWord.copy(id = id)
            _showAddWordDialog.value = false
        }
    }

    fun deleteWord(word: WordEntity) {
        viewModelScope.launch {
            wordDao.deleteWord(word)
            if (_selectedWord.value?.id == word.id) {
                _selectedWord.value = null
            }
        }
    }

    fun openAddWordDialog() {
        _showAddWordDialog.value = true
    }

    fun closeAddWordDialog() {
        _showAddWordDialog.value = false
    }

    fun openAiAddDialog() {
        _aiWordError.value = null
        _showAiAddDialog.value = true
    }

    fun closeAiAddDialog() {
        _showAiAddDialog.value = false
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

            val result = openRouterService.generateWordDefinition(
                apiKey = key,
                model = openRouterModel.value,
                term = trimmed
            )

            result.fold(
                onSuccess = { generatedEntity ->
                    val insertedId = wordDao.insertWord(generatedEntity)
                    val fullWord = generatedEntity.copy(id = insertedId)
                    _selectedWord.value = fullWord
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
        val key = openRouterApiKey.value
        if (key.isBlank()) {
            _openRouterError.value = "OpenRouter API Key not set. Tap Settings to enter your key."
            _showSettingsDialog.value = true
            return
        }

        viewModelScope.launch {
            _openRouterLoading.value = true
            _openRouterError.value = null
            _openRouterResult.value = null

            val result = openRouterService.queryWordInsights(
                apiKey = key,
                model = openRouterModel.value,
                term = word.term,
                existingMeaning = word.humanMeaning
            )

            result.fold(
                onSuccess = { content ->
                    _openRouterResult.value = content
                    _openRouterLoading.value = false
                },
                onFailure = { error ->
                    _openRouterError.value = error.message ?: "Failed to query OpenRouter AI"
                    _openRouterLoading.value = false
                }
            )
        }
    }

    fun clearOpenRouterOutput() {
        _openRouterResult.value = null
        _openRouterError.value = null
    }
}
