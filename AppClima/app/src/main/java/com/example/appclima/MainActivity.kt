package com.example.appclima

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.location.Location
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.appclima.components.WeatherScreen
import com.example.appclima.network.RetrofitClient
import com.example.appclima.network.WeatherService
import com.example.appclima.utils.obtainLocation
import kotlinx.coroutines.launch
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("WeatherApp", "PERMISSION GRANTED")
            } else {
                Log.d("WeatherApp", "PERMISSION DENIED")
            }
        }

        setContent {
            WeatherApp(permissionLauncher = permissionLauncher)
        }
    }
}

@Composable
fun WeatherApp(permissionLauncher: ActivityResultLauncher<String>) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var location by remember { mutableStateOf<Location?>(null) }
    var weatherResponse by remember { mutableStateOf<WeatherService.WeatherResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val checkPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(checkPermission) {
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted
            try {
                location = obtainLocation(context)
                location?.let { loc ->
                    isLoading = true
                    val weatherService = RetrofitClient.instance.create(WeatherService::class.java)
                    weatherService.getCurrentWeatherByLocation(
                        latitude = loc.latitude,
                        longitude = loc.longitude,
                        apiKey = "d1c516ca9f581d090ab817c4f2dbca1c"
                    ).enqueue(object : retrofit2.Callback<WeatherService.WeatherResponse> {
                        override fun onResponse(
                            call: retrofit2.Call<WeatherService.WeatherResponse>,
                            response: retrofit2.Response<WeatherService.WeatherResponse>
                        ) {
                            isLoading = false
                            if (response.isSuccessful) {
                                weatherResponse = response.body()
                            } else {
                                errorMessage = "Error: ${response.message()}"
                            }
                        }

                        override fun onFailure(
                            call: retrofit2.Call<WeatherService.WeatherResponse>,
                            t: Throwable
                        ) {
                            isLoading = false
                            errorMessage = "Error: ${t.localizedMessage}"
                        }
                    })
                }
            } catch (e: Exception) {
                isLoading = false
                errorMessage = "Error: ${e.localizedMessage}"
            }
        } else {
            // Request permission
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading) {
            Text(text = "Loading...")
        } else if (errorMessage != null) {
            Text(text = errorMessage ?: "Unknown Error")
        } else {
            location?.let {
                weatherResponse?.let { weather ->
                    WeatherScreen(
                        location = "${it.latitude}, ${it.longitude}",
                        temperature = "${weather.main.temp}°C",
                        description = weather.weather.firstOrNull()?.description ?: "N/A",
                        humidity = "${weather.main.humidity}%",
                        windSpeed = "${weather.wind.speed} km/h",
                        rainProbability = "${weather.rain?.`1h` ?: "N/A"} mm",
                        nextDaysForecast = listOf(
                            "Mon: 24°C",
                            "Tue: 26°C",
                            "Wed: 22°C"
                        ) // Example data for next days
                    )
                } ?: run {
                    Text(text = "No weather data available")
                }
            } ?: run {
                Text(text = "Obtaining location...")
            }
        }
    }
}
