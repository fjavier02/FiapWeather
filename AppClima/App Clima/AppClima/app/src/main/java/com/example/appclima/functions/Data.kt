package com.example.appclima.functions

import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

class DateUtil {
    companion object {
        private val locale = Locale("pt", "BR")

        fun getCurrentDayOfWeek(): String {
            val currentDateTime = LocalDateTime.now()
            val dayOfWeek = currentDateTime.dayOfWeek
            return dayOfWeek.getDisplayName(TextStyle.FULL, locale) // Use o locale definido
        }

        fun getCurrentDayOfMonth(): Int {
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.dayOfMonth
        }

        fun getFormattedDate(): String {
            val dayOfWeek = getCurrentDayOfWeek()
            val dayOfMonth = getCurrentDayOfMonth()
            return "$dayOfWeek, $dayOfMonth"
        }

        fun getNextDaysOfWeek(startDate: LocalDateTime, numberOfDays: Int): List<String> {
            val days = mutableListOf<String>()
            var currentDate = startDate
            for (i in 0 until numberOfDays) {
                currentDate = currentDate.plusDays(1)
                val dayOfWeek = currentDate.dayOfWeek
                days.add(dayOfWeek.getDisplayName(TextStyle.FULL, locale))
            }
            return days
        }

        fun getMensagemEntrada(): String {
            val currentDateTime = LocalDateTime.now()
            val hour = currentDateTime.hour
            return when {
                hour in 0..11 -> "Bom dia"
                hour in 12..17 -> "Boa tarde"
                else -> "Boa noite"
            }
        }
    }
}
