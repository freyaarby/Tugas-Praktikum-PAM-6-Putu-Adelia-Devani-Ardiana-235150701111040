package com.example.projectpraktikumpam6.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    fun formatTanggal(date: Date): String {
        return dateFormat.format(date)
    }

    fun parseTanggal(tanggal: String): Date? {
        return try {
            dateFormat.parse(tanggal)
        } catch (e: Exception) {
            null
        }
    }
}
