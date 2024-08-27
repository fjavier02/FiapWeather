package com.example.appclima.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric" // Para obter a temperatura em Celsius
    ): Call<WeatherResponse>

    @GET("weather")
    fun getCurrentWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric" // Para obter a temperatura em Celsius
    ): Call<WeatherResponse>

    data class WeatherResponse(
        val main: Main,
        val weather: List<Weather>,
        val wind: Wind,
        val rain: Rain?
    )

    data class Main(
        val temp: Float,
        val humidity: Int
    )

    data class Weather(
        val description: String,
        @SerializedName("icon") val icon: String
    )

    data class Wind(
        val speed: Float
    )

    data class Rain(
        @SerializedName("1h") val `1h`: Float?
    )
}
