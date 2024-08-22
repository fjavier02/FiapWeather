package com.example.appclima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.appclima.components.WeatherScreen
import com.example.appclima.ui.theme.AppClimaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClimaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WeatherScreen()
                }
            }
        }
    }
}