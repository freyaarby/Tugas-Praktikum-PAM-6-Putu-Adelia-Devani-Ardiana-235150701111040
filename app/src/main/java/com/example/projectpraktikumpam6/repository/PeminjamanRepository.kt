package com.example.projectpraktikumpam6.repository

import com.example.projectpraktikumpam6.database.PeminjamanDao
import com.example.projectpraktikumpam6.database.Peminjaman

class PeminjamanRepository(private val peminjamanDao: PeminjamanDao) {

    suspend fun insert(peminjaman: Peminjaman) {
        peminjamanDao.insert(peminjaman)
    }

    suspend fun update(peminjaman: Peminjaman) {
        peminjamanDao.update(peminjaman)
    }

    suspend fun delete(peminjaman: Peminjaman) {
        peminjamanDao.delete(peminjaman)
    }

    fun getAllPeminjaman() = peminjamanDao.getAllPeminjaman()
}
