package com.example.projectpraktikumpam6.helper

object ValidationHelper {

    fun isValidNama(nama: String): Boolean {
        return nama.isNotEmpty() && nama.length >= 3
    }

    fun isValidTanggal(tanggal: String): Boolean {
        return tanggal.matches(Regex("\\d{2}-\\d{2}-\\d{4}"))
    }
}
