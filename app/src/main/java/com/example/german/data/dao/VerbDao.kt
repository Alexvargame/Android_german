package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.Verb

@Dao
interface VerbDao {
    @Insert
    suspend fun insert(verb: Verb)

    @Insert
    suspend fun insertAll(verbs: List<Verb>)

    @Query("SELECT * FROM words_verb")
    suspend fun getAll(): List<Verb>

    @Query("SELECT * FROM words_verb WHERE word_ptr_id = :id")
    suspend fun getById(id: Long): Verb?
}
