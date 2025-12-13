package com.example.german.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect

import com.example.german.data.ui.user_profile.UserProfileViewModel
import com.example.german.data.ui.autorization.AutorizationViewModel
@Composable
fun User_profile_screen(
    userviewModel: UserProfileViewModel,
    navController: NavController,
) {

    val user by userviewModel.currentUser
    val context = LocalContext.current
    Log.d("AUTO_USERSCREEN", "${user}")
    Log.d("AUTO_USERSCREEN_MODEL", "${userviewModel.currentUser} , ${userviewModel}")
    if (user == null) {
        Log.d("AUTO_USERSCREEN_NULL", "${user}")
        // Если кто-то попал без логина — вернём на старт
        LaunchedEffect(Unit) { navController.navigate("start_app_screen") { popUpTo(0) } }
        return
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Добро пожаловать, ",
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { /* TODO вход */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ваш профиль")
        }
        Button(
            onClick = { /* TODO вход */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Упражнения")
        }
        Button(
            onClick = { /* TODO вход */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ЧТО ТО")
        }

        Button (
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Назад")
        }
        Button (
            onClick = {
                (context as? Activity)?.finish()
                       },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Выйти")
        }

    }
}