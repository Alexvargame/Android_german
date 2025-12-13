package com.example.german.navigation



import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.german.data.AppDatabase
import com.example.german.data.ui.autorization.AutorizationViewModel
import com.example.german.data.repository.autorization.AutorizationViewModelFactory
import com.example.german.data.ui.user_profile.UserProfileViewModel
import com.example.german.ui.screens.Exercises_screen
import com.example.german.ui.screens.Start_app_screen
import com.example.german.ui.screens.User_profile_screen
import com.example.german.ui.screens.Registration_screen
import com.example.german.data.ui.registration.RegistrationViewModel
import com.example.german.data.repository.RegistrationViewModelFactory
import com.example.german.data.repository.UserRegistrationRepository

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "start_app_screen") {

        // Экран входа
       // composable("home"){MyApp(navController, greetingText = greetingText)}
        composable("start_app"){
            val context = LocalContext.current
            val db = AppDatabase.getInstance(context)
            val factory = AutorizationViewModelFactory(db)
            val autoviewModel: AutorizationViewModel =
                viewModel(factory = factory)
            val userviewModel: UserProfileViewModel = viewModel()
            Start_app_screen(userviewModel, autoviewModel, navController) }
        composable("start_app") {
            val context = LocalContext.current
            val db = AppDatabase.getInstance(context)

            val autoviewModel: AutorizationViewModel =
                viewModel(factory = AutorizationViewModelFactory(db))

            // Единая ViewModel профиля для всего графа
            val userviewModel: UserProfileViewModel = viewModel(key = "userProfile")

            Start_app_screen(userviewModel, autoviewModel, navController)
        }

        // Экран регистрации
        /*composable("registration") {
            val context = LocalContext.current
            val repo = UserRegistrationRepository(AppDatabase.getInstance(context).registrationDao())
            val factory = RegistrationViewModelFactory(repo)
            val registrationViewModel: RegistrationViewModel =
                viewModel(factory = factory)

            Registration_screen(registrationViewModel, navController)
        }*/

        // Экран профиля
        composable("user_profile_screen") {
            val userViewModel: UserProfileViewModel = viewModel(key = "userProfile")
            User_profile_screen(userViewModel, navController)
        }

        // Экран упражнений

    }
}
