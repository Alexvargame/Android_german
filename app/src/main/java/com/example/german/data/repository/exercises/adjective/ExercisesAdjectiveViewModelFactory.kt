package com.example.german.data.repository.exercises.adjective

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

import com.example.german.data.AppDatabase
import com.example.german.data.ui.viewModel.exercises.adjective.ExercisesAdjectiveViewModel

class ExercisesAdjectiveViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExercisesAdjectiveViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExercisesAdjectiveViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}