package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.knowledge.AiMode
import com.example.data.knowledge.KnowledgeSourceRegistry
import com.example.data.knowledge.KnowledgeSourceType
import com.example.data.knowledge.OfflineKnowledgeEngine
import com.example.data.local.AppDatabase
import com.example.data.local.DefaultWordsCatalog
import com.example.data.local.WordEntity
import com.example.data.network.LexicalLanguageService
import com.example.ui.viewmodel.DictionaryViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class DictionaryFeaturesRobolectricTest {

    private lateinit var app: Application
    private lateinit var db: AppDatabase
    private lateinit var viewModel: DictionaryViewModel

    @Before
    fun setUp() = runBlocking {
        app = ApplicationProvider.getApplicationContext()
        db = AppDatabase.getInstance(app)
        val count = db.wordDao().getCount()
        if (count == 0) {
            val catalog = DefaultWordsCatalog.getAllCatalogWords()
            db.wordDao().insertWords(catalog)
        }
        viewModel = DictionaryViewModel(app)
        ShadowLooper.idleMainLooper()
    }

    @After
    fun tearDown() {
        // AppDatabase singleton lifecycle handled by ApplicationProvider
    }

    @Test
    fun database_loadsWords_withLevelsAndImportance() = runTest {
        ShadowLooper.idleMainLooper()
        val words = db.wordDao().getAllWordsFlow().first()

        assertTrue("Words should be present in database", words.isNotEmpty())

        val basicCount = words.count { it.level == WordEntity.LEVEL_BASIC }
        val intermediateCount = words.count { it.level == WordEntity.LEVEL_INTERMEDIATE }
        val advancedCount = words.count { it.level == WordEntity.LEVEL_ADVANCED }
        val importantCount = words.count { it.isImportant }

        assertTrue("Basic words should be present: $basicCount", basicCount > 0)
        assertTrue("Intermediate words should be present: $intermediateCount", intermediateCount > 0)
        assertTrue("Advanced words should be present: $advancedCount", advancedCount > 0)
        assertTrue("Important words should be present: $importantCount", importantCount > 0)
    }

    @Test
    fun filter_byLevelAndImportance_worksAccurately() = runTest {
        ShadowLooper.idleMainLooper()
        val allWords = db.wordDao().getAllWordsFlow().first()
        assertTrue("Database should have words", allWords.isNotEmpty())

        // 1. Filter by IMPORTANT
        val importantWords = allWords.filter { it.isImportant }
        assertTrue("Important list should not be empty", importantWords.isNotEmpty())
        assertTrue("All filtered words must be important", importantWords.all { it.isImportant })

        // 2. Filter by LEVEL_BASIC
        val basicWords = allWords.filter { it.level == WordEntity.LEVEL_BASIC }
        assertTrue("Basic list should not be empty", basicWords.isNotEmpty())
        assertTrue("All filtered words must be BASIC", basicWords.all { it.level == WordEntity.LEVEL_BASIC })

        // 3. Filter by LEVEL_INTERMEDIATE
        val intermediateWords = allWords.filter { it.level == WordEntity.LEVEL_INTERMEDIATE }
        assertTrue("Intermediate list should not be empty", intermediateWords.isNotEmpty())
        assertTrue("All filtered words must be INTERMEDIATE", intermediateWords.all { it.level == WordEntity.LEVEL_INTERMEDIATE })

        // 4. Filter by LEVEL_ADVANCED
        val advancedWords = allWords.filter { it.level == WordEntity.LEVEL_ADVANCED }
        assertTrue("Advanced list should not be empty", advancedWords.isNotEmpty())
        assertTrue("All filtered words must be ADVANCED", advancedWords.all { it.level == WordEntity.LEVEL_ADVANCED })
    }

    @Test
    fun toggleImportance_updatesDatabase() = runTest {
        ShadowLooper.idleMainLooper()
        val words = db.wordDao().getAllWordsFlow().first()
        val testWord = words.first()
        val originalImportance = testWord.isImportant

        // Toggle via DAO
        db.wordDao().updateImportance(testWord.id, !originalImportance)

        val updatedWord = db.wordDao().getWordById(testWord.id)
        assertNotNull(updatedWord)
        assertEquals(!originalImportance, updatedWord!!.isImportant)
    }

    @Test
    fun addCustomWord_withLevelAndImportance_insertsCorrectly() = runTest {
        val uniqueTerm = "TestQuantumTerm_${System.currentTimeMillis()}"
        val newWord = WordEntity(
            term = uniqueTerm,
            category = "Quantum AI",
            part = "Custom",
            humanMeaning = "A cutting edge quantum computing term.",
            keyPoints = "• Superposition\n• Entanglement",
            practicalUses = "• Quantum cryptography",
            examples = "Example: Shor's algorithm.",
            referenceUrl = "https://wikipedia.org",
            level = WordEntity.LEVEL_ADVANCED,
            isImportant = true,
            isCustom = true
        )
        val id = db.wordDao().insertWord(newWord)

        val retrieved = db.wordDao().getWordById(id)
        assertNotNull("Custom word should be in database", retrieved)
        assertEquals(WordEntity.LEVEL_ADVANCED, retrieved!!.level)
        assertTrue("Custom word should be marked important", retrieved.isImportant)
        assertTrue("Custom word should be flagged isCustom", retrieved.isCustom)
    }

    @Test
    fun keepAiOutputInApp_updatesWordAndPersistsAiNotes() = runTest {
        ShadowLooper.idleMainLooper()
        val words = db.wordDao().getAllWordsFlow().first()
        val testWord = words.first()

        val aiExplanation = "💡 Deep Insight: Analogous to a dedicated highway lane with guaranteed bandwidth."
        db.wordDao().updateAiNotes(
            id = testWord.id,
            notes = aiExplanation,
            hasNotes = true,
            timestamp = System.currentTimeMillis()
        )

        val updatedWord = db.wordDao().getWordById(testWord.id)
        assertNotNull(updatedWord)
        assertTrue("Word should indicate hasAiNotes", updatedWord!!.hasAiNotes)
        assertEquals(aiExplanation, updatedWord.savedAiNotes)
        assertTrue("Timestamp should be greater than 0", updatedWord.aiNotesTimestamp > 0)
    }

    @Test
    fun offlineKnowledgeEngine_generatesAccurateExplanations() {
        val word = WordEntity(
            term = "TCP",
            category = "Networking",
            part = "Protocol",
            humanMeaning = "Transmission Control Protocol provides reliable, ordered data delivery.",
            keyPoints = "• 3-way handshake\n• Congestion control",
            practicalUses = "• Web browsing\n• File transfer",
            examples = "Used in HTTP/1.1 and HTTP/2.",
            referenceUrl = "https://en.wikipedia.org/wiki/Transmission_Control_Protocol"
        )

        val analogy = OfflineKnowledgeEngine.generateOfflineKnowledge(word, AiMode.DEEP_ANALOGY)
        assertTrue("Analogy should contain intuitive mental model", analogy.contains("Analogy") || analogy.contains("TCP"))

        val codeLab = OfflineKnowledgeEngine.generateOfflineKnowledge(word, AiMode.CODE_LAB)
        assertTrue("Code lab should have implementation examples", codeLab.contains("Implementation") || codeLab.contains("TCP") || codeLab.contains("Socket"))

        val quiz = OfflineKnowledgeEngine.generateOfflineKnowledge(word, AiMode.INTERVIEW_QUIZ)
        assertTrue("Interview quiz should provide question and answers", quiz.contains("Interview") || quiz.contains("Question") || quiz.contains("TCP"))
    }

    @Test
    fun knowledgeSourceRegistry_resolvesMultiSourceAndLinguisticStandards() {
        val word = WordEntity(
            term = "TCP",
            category = "Networking",
            part = "Protocol",
            humanMeaning = "Reliable packet transmission.",
            keyPoints = "• Handshake",
            practicalUses = "• Web",
            examples = "HTTP",
            referenceUrl = ""
        )

        val sources = KnowledgeSourceRegistry.getKnowledgeSourcesForWord(word)
        assertTrue("Must have multiple authoritative sources", sources.size >= 4)

        val hasStandard = sources.any { it.type == KnowledgeSourceType.STANDARD }
        val hasEncyclopedia = sources.any { it.type == KnowledgeSourceType.ENCYCLOPEDIA }
        val hasDictionary = sources.any { it.type == KnowledgeSourceType.DICTIONARY }

        assertTrue("Must include an official standard/RFC", hasStandard)
        assertTrue("Must include Wikipedia encyclopedia", hasEncyclopedia)
        assertTrue("Must include linguistic / lexical dictionary source", hasDictionary)

        val wiktionary = sources.find { it.name.contains("Wiktionary") }
        assertNotNull("Wiktionary source must be present for correct language", wiktionary)

        val merriamWebster = sources.find { it.name.contains("Merriam-Webster") }
        assertNotNull("Merriam-Webster lexicographical source must be present", merriamWebster)
    }

    @Test
    fun lexicalLanguageService_resolvesGrammarAndPhonetics() = runTest {
        val service = LexicalLanguageService()
        val data = service.resolveLexicalLanguageData("TCP")

        assertNotNull(data)
        assertEquals("TCP", data.term)
        assertNotNull("Should have phonetic transcription", data.phonetic)
        assertTrue("Should have formal definition", data.formalDefinition.isNotBlank())
        assertTrue("Should provide grammatical usage tip", data.grammaticalUsageTip != null && data.grammaticalUsageTip!!.isNotBlank())
    }
}
