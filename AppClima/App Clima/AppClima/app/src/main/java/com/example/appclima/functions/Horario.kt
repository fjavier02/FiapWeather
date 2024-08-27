package com.example.appclima.functions

import java.time.LocalTime

class MensagemEntrada {
    companion object {
        fun getMensagemEntrada(): String {
            val currentTime = LocalTime.now()
            return when (currentTime.hour) {
                in 5..11 -> "Bom dia!"
                in 12..17 -> "Boa tarde!"
                in 18..22 -> "Boa noite!"
                else -> "Boa madrugada!" // Para horas de 23 a 4
            }
        }
    }
}