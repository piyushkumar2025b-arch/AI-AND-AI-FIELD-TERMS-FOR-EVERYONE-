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
}
