package com.example.appclima.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appclima.network.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherService.WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherService.WeatherResponse?> = _weatherData

    private val weatherService: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                val response = weatherService.getCurrentWeatherByCity(city, "d1c516ca9f581d090ab817c4f2dbca1c").execute()
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                } else {
                    // Tratamento de erro: você pode definir uma mensagem de erro em _weatherData ou criar um novo fluxo de estado para erros
                    _weatherData.value = null // ou uma estratégia de tratamento de erro diferente
                }
            } catch (e: Exception) {
                // Tratamento de exceções: logar ou definir uma mensagem de erro
                _weatherData.value = null // ou uma estratégia de tratamento de erro diferente
            }
        }
    }
}
