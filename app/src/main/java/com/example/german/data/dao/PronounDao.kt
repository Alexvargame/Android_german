package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.Pronoun

@Dao
interface PronounDao {
    @Insert
    suspend fun insert(pronoun: Pronoun)

    @Insert
    suspend fun insertAll(pronouns: List<Pronoun>)

    @Query("SELECT * FROM words_pronoun")
    suspend fun getAll(): List<Pronoun>

    @Query("SELECT * FROM words_pronoun WHERE word_ptr_id = :id")
    suspend fun getById(id: Long): Pronoun?
}


