package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.OtherWord

@Dao
interface OtherWordDao {
    @Insert
    suspend fun insert(otherWord: OtherWord)

    @Insert
    suspend fun insertAll(otherWords: List<OtherWord>)

    @Query("SELECT * FROM words_otherwords")
    suspend fun getAll(): List<OtherWord>

    @Query("SELECT * FROM words_otherwords WHERE word_ptr_id = :id")
    suspend fun getById(id: Long): OtherWord?
}

