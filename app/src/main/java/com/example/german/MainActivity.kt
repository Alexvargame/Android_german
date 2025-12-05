package com.example.german

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text            // ← ДОБАВЛЕНО: Text виджет для отображения текста
//import androidx.compose.material3.Button          // ← ДОБАВЛЕНО: Button виджет для кнопки
import androidx.compose.runtime.*                // ← ДОБАВЛЕНО: Composable, remember, mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
// ↓↓↓ ДОБАВЛЕННЫЕ импорты для Column, Modifier, fillMaxSize, padding, Alignment, dp
import androidx.compose.foundation.layout.Column         // ← ДОБАВЛЕНО
import androidx.compose.foundation.layout.fillMaxSize    // ← ДОБАВЛЕНО
import androidx.compose.foundation.layout.padding       // ← ДОБАВЛЕНО
import androidx.compose.foundation.layout.Arrangement   // ← ДОБАВЛЕНО
import androidx.compose.ui.Alignment                   // ← ДОБАВЛЕНО
import androidx.compose.ui.Modifier                    // ← ДОБАВЛЕНО
import androidx.compose.ui.unit.dp                      // ← ДОБАВЛЕНО
import androidx.compose.ui.graphics.Color

import java.util.Calendar
import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.border
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.sp                 // для fontSize
import androidx.compose.ui.text.style.TextOverflow // для overflow = TextOverflow.Ellipsis
//import androidx.compose.foundation.layout.Box
import androidx.compose.ui.platform.LocalContext
import android.app.Activity



// -------------------------
// Класс MainActivity
// Наследуется от ComponentActivity, как в шаблоне Empty Compose Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TestDb(this).testBookDao()
        val hours = 18
        val greetingText = if (getCurrentHour() < hours) {
            "Добрый день"
        } else {
            "Добрый вечер"
        }
        //enableEdgeToEdge()      // включаем Edge-to-edge
        // setContent запускает Composable функцию на экране
        // добавлено MyApp() вместо обычного setContentView(R.layout.activity_main)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home"){MyApp(navController, greetingText = greetingText)}
                composable("start_app"){Start_app_screen()}
                composable("registration"){Registration_screen()}

            }
        }
    }
}

// -------------------------
// Composable функция MyApp
// Здесь описывается весь UI приложения
@Composable
fun MyApp(navController: NavController, greetingText: String) {
    // state переменная
    val context = LocalContext.current
    var textHello by remember { mutableStateOf("$greetingText,\nхотите знать немецкий?") }
    //var text_login by remember { mutableStateOf(value = "Войти") }
    //var text_reg by remember { mutableStateOf(value = "Зарегистрироваться") }
    //var text_by by remember { mutableStateOf(value = "Очень жаль") }
    // -------------------------


    androidx.compose.material3.MaterialTheme {  // ← ДОБАВЛЕНО
        Column(
            modifier = Modifier                   // ← ИСПРАВЛЕНО: заменено на импортированный Modifier
                .fillMaxSize()                    // ← ДОБАВЛЕНО: чтобы занять весь экран
                .padding(36.dp),                  // ← ДОБАВЛЕНО: отступы
        )
        {

            Spacer(modifier = Modifier.height(40.dp))
            Text(textHello,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 24.sp,      // размер шрифта
                maxLines = 2,          // максимум 2 строки
                overflow = TextOverflow.Ellipsis // если вдруг текст длинный
            )
            Column(
                modifier = Modifier                   // ← ИСПРАВЛЕНО: заменено на импортированный Modifier
                    .fillMaxSize()                    // ← ДОБАВЛЕНО: чтобы занять весь экран
                    .padding(36.dp),                  // ← ДОБАВЛЕНО: отступы
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                GreetingButton( text = "Войти",
                    onClick = { navController.navigate("start_app") })
                Spacer(modifier = Modifier.height(16.dp))
                GreetingButton( text = "Зарегистрироваться",
                    onClick = { navController.navigate("registration") })
                Spacer(modifier = Modifier.height(16.dp))
                GreetingButton(text = "Очень жаль",
                    onClick = {(context as? Activity)?.finish()})
            }
        }
    }
}
/*@Composable
fun Start_app_screen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Start", fontSize = 24.sp, color = Color.White)
    }
}

@Composable
fun Registration_screen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Registration", fontSize = 24.sp, color = Color.White)
    }
}

*/
/*@Composable
fun GreetingButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        modifier = modifier
            .border(2.dp, Color.Black, RoundedCornerShape(50))
            .background(Color.White, RoundedCornerShape(50))
            .padding(10.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            textAlign = TextAlign.Center
        )
    }
}
*/

fun getCurrentHour(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR_OF_DAY) // вернёт час от 0 до 23
}
// -------------------------
// Preview-функция для Android Studio
// Позволяет увидеть UI прямо в редакторе, без запуска на телефоне
@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    val navController = rememberNavController()  // фиктивный NavController для превью

    MyApp(navController = navController,  greetingText = "Добрый день")  // <-- вызов Composable функции для предпросмотра
}
