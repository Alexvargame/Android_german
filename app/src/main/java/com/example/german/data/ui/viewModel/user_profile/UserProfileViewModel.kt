package com.example.german.data.ui.viewModel.user_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.example.german.data.AppDatabase
import com.example.german.data.entities.BaseUser
import com.example.german.data.dao.BaseUserDao

class UserProfileViewModel ( private val userDao: BaseUserDao): ViewModel() {

    private val _currentUser = mutableStateOf<BaseUser?>(null)
    val currentUser: State<BaseUser?> = _currentUser
    init {
        Log.d("VM_LIFECYCLE", "UserProfileViewModel CREATED: $this")
    }

    fun setUser(user: BaseUser) {
        _currentUser.value = user
        Log.d("AUTO_USERMODEL","setUser -> $user in $this")
    }

    fun logout() {
        Log.d("AUTO_USERMODEL","LOGOUT_USER")
        _currentUser.value = null
    }

    fun isAuthorized(): Boolean {
        Log.d("AUTO_RISED","${_currentUser.value}")
        return _currentUser.value != null
    }
    fun decreaseLife() {
        Log.d("USER_DECREASE","setUser -> ")
        currentUser.value?.let { user ->
            val lifes = user.lifes ?: 0
            if (lifes > 0) {
                Log.d("USER_DECREASE_USER","setUser -> ${user}")
                val updatedUser = user.copy(lifes = lifes - 1)
                Log.d("USER_DECREASE_LIFES","setUser -> ${updatedUser}")
                _currentUser.value = updatedUser//user.copy(lifes = lifes - 1)
                saveCurrentUser()
            }
        }
    }

    fun addScore(points: Int) {
        currentUser.value?.let { user ->
            val score = user.score ?: 0
            Log.d("USER_ADDCORE_USER","setUser -> ${user}")
            val updatedUser = user.copy(score = score + points)
            Log.d("USER_ADDCORE_LIFES","setUser -> ${updatedUser}")
            _currentUser.value = updatedUser //user.copy(score = score + points)
            saveCurrentUser()
        }
    }
    private fun saveCurrentUser() {
        currentUser.value?.let { user ->
            viewModelScope.launch {
                try {
                    userDao.update(user)
                    Log.d("USER_DB_SAVE", "User saved: $user")
                } catch (e: Exception) {
                    Log.e("USER_DB_SAVE", "Error saving user", e)
                }
            }
        }
    }

    private fun saveUserToDatabase(user: BaseUser) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(user)  // Сохраняем или обновляем пользователя в базе
        }
    }
    fun loadUserById(userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userFromDb = userDao.getById(userId)
                userFromDb?.let {
                    // Перекладываем в main thread, чтобы Compose увидел изменения
                    _currentUser.value = it
                    Log.d("AUTO_USERMODEL","loadUserById -> $it")
                }
            } catch (e: Exception) {
                Log.e("AUTO_USERMODEL", "Error loading user by ID", e)
            }
        }
    }
}
















