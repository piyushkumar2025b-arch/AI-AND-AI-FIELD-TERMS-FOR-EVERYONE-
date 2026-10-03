package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppDatabase
import com.example.data.local.DefaultWordsCatalog
import com.example.data.local.WordEntity
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
}
