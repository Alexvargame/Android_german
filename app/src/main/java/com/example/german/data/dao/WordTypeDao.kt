package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.WordType

@Dao
interface WordTypeDao {
    @Insert
    suspend fun insert(wordType: WordType)

    @Insert
    suspend fun insertAll(wordTypes: List<WordType>)

    @Query("SELECT * FROM words_wordtype")
    suspend fun getAll(): List<WordType>

    @Query("SELECT * FROM words_wordtype WHERE id = :id")
    suspend fun getById(id: Long): WordType?
}

