package com.example.crosspostersandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.crosspostersandroid.ui.theme.CrossPostersAndroidTheme
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrossPostersAndroidTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = "Posters", fontSize = 32.sp, fontWeight = FontWeight.Bold) }) }
                ) {
                    MainScreen(it)
                }
            }
        }
    }
}


