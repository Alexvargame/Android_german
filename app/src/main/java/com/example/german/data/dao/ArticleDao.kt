package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.german.data.entities.Article

@Dao
interface ArticleDao {

    @Insert
    suspend fun insert(article: Article)

    @Insert
    suspend fun insertAll(articles: List<Article>)

    @Query("SELECT * FROM words_article")
    suspend fun getAll(): List<Article>

    @Query("SELECT * FROM words_article WHERE id = :id")
    suspend fun getById(id: Long): Article?

    @Update
    suspend fun update(article: Article)
}


