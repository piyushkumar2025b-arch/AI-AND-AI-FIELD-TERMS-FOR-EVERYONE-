package com.example.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    indices = [
        Index(value = ["term"], unique = true),
        Index(value = ["category"]),
        Index(value = ["part"])
    ]
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val term: String,
    val category: String,
    val part: String,
    val humanMeaning: String,
    val keyPoints: String, // newline-separated bullet points
    val practicalUses: String,
    val examples: String,
    val referenceUrl: String = "",
    val isCustom: Boolean = false,
    val isBookmarked: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
