package com.example.german

import android.content.Context
import android.util.Log
import com.example.german.data.AppDatabase
import com.example.german.data.entities.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestDb(private val context: Context) {

    fun testBookDao() {
        val db = AppDatabase.getInstance(context) // используем метод получения базы
        val bookDao = db.bookDao()

        CoroutineScope(Dispatchers.IO).launch {
            // Вставка тестовой книги
            val testBook = Book(name = "Тестовая книга", description = "Описание книги")
            bookDao.insert(testBook)

            // Чтение всех книг
            val books = bookDao.getAll()
            books.forEach {
                Log.d("TEST_DB", "Book: ${it.name} / ${it.description}")
            }
        }
    }
}


