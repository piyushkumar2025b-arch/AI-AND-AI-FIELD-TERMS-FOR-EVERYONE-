package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words ORDER BY LOWER(term) ASC")
    fun getAllWordsFlow(): Flow<List<WordEntity>>

    @Query("""
        SELECT * FROM words 
        WHERE LOWER(term) LIKE '%' || LOWER(:query) || '%' 
           OR LOWER(humanMeaning) LIKE '%' || LOWER(:query) || '%'
           OR LOWER(category) LIKE '%' || LOWER(:query) || '%'
        ORDER BY LOWER(term) ASC
    """)
    fun searchWordsFlow(query: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE LOWER(term) LIKE LOWER(:prefix) || '%' ORDER BY LOWER(term) ASC")
    fun getWordsByPrefixFlow(prefix: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE part = :part ORDER BY LOWER(term) ASC")
    fun getWordsByPartFlow(part: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE isBookmarked = 1 ORDER BY LOWER(term) ASC")
    fun getBookmarkedWordsFlow(): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE id = :id LIMIT 1")
    suspend fun getWordById(id: Long): WordEntity?

    @Query("SELECT * FROM words WHERE LOWER(term) = LOWER(:term) LIMIT 1")
    suspend fun getWordByTerm(term: String): WordEntity?

    @Query("SELECT COUNT(*) FROM words")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity): Long

    @Update
    suspend fun updateWord(word: WordEntity)

    @Delete
    suspend fun deleteWord(word: WordEntity)

    @Query("UPDATE words SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateBookmark(id: Long, isBookmarked: Boolean)

    @Query("UPDATE words SET isImportant = :isImportant WHERE id = :id")
    suspend fun updateImportance(id: Long, isImportant: Boolean)

    @Query("UPDATE words SET level = :level WHERE id = :id")
    suspend fun updateLevel(id: Long, level: String)

    @Query("UPDATE words SET savedAiNotes = :notes, hasAiNotes = :hasNotes, aiNotesTimestamp = :timestamp WHERE id = :id")
    suspend fun updateAiNotes(id: Long, notes: String, hasNotes: Boolean, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE words SET humanMeaning = :meaning WHERE id = :id")
    suspend fun updateHumanMeaning(id: Long, meaning: String)

    @Query("SELECT * FROM words WHERE hasAiNotes = 1 ORDER BY LOWER(term) ASC")
    fun getAiKeptWordsFlow(): Flow<List<WordEntity>>
}
