package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.Numeral

@Dao
interface NumeralDao {
    @Insert
    suspend fun insert(numeral: Numeral)

    @Insert
    suspend fun insertAll(numerals: List<Numeral>)

    @Query("SELECT * FROM words_numeral")
    suspend fun getAll(): List<Numeral>

    @Query("SELECT * FROM words_numeral WHERE word_ptr_id = :id")
    suspend fun getById(id: Long): Numeral?
}

