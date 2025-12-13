package com.example.german.data.repository.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

import com.example.german.data.AppDatabase
import com.example.german.data.ui.user_profile.UserProfileViewModel
import com.example.german.ui.screens.User_profile_screen
import kotlinx.serialization.builtins.UShortArraySerializer

class UserProfileViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
  /*  override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserProfileViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }*/
}
