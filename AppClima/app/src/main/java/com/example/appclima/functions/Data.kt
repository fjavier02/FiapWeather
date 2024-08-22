package com.example.appclima.functions

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class DateUtil {
    companion object {
        private val locale = Locale("pt", "BR")

        fun getCurrentDayOfWeek(): String {
            val currentDate = LocalDate.now()
            val dayOfWeek = currentDate.dayOfWeek
            return dayOfWeek.getDisplayName(TextStyle.FULL, locale) // Use o locale definido
        }

        fun getCurrentDayOfMonth(): Int {
            val currentDate = LocalDate.now()
            return currentDate.dayOfMonth
        }

        fun getFormattedDate(): String {
            val dayOfWeek = getCurrentDayOfWeek()
            val dayOfMonth = getCurrentDayOfMonth()
            return "$dayOfWeek, $dayOfMonth"
        }

        fun getNextDaysOfWeek(startDate: LocalDate, numberOfDays: Int): List<String> {
            val days = mutableListOf<String>()
            var currentDate = startDate
            for (i in 0 until numberOfDays) {
                currentDate = currentDate.plusDays(1)
                val dayOfWeek = currentDate.dayOfWeek
                days.add(dayOfWeek.getDisplayName(TextStyle.FULL, locale))
            }
            return days
        }
    }
}
