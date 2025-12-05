package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.Word

@Dao
interface WordDao {
    @Insert
    suspend fun insert(word: Word)

    @Insert
    suspend fun insertAll(words: List<Word>)

    @Query("SELECT * FROM words_word")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM words_word WHERE id = :id")
    suspend fun getById(id: Long): Word?
}

