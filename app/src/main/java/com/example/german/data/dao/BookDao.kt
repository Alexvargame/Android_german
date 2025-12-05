package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.german.data.entities.Book

@Dao
interface BookDao {
    @Insert
    suspend fun insert(book: Book)

    @Query("SELECT * FROM words_book")
    suspend fun getAll(): List<Book>
}
