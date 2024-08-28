package com.example.appclima.utils

import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

suspend fun obtainLocation(context: Context): Location? {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    return try {
        fusedLocationClient.lastLocation.await()
    } catch (e: SecurityException) {
        null // Retorna null se não tiver permissão
    } catch (e: Exception) {
        null // Trate outras exceções como necessário
    }
}


