package com.example.german.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.Lection

@Dao
interface LectionDao {
    @Insert
    suspend fun insert(lection: Lection)

    @Query("SELECT * FROM words_lection")
    suspend fun getAll(): List<Lection>
}
