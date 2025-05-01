package com.example.projectpraktikumpam6.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peminjaman_table")
data class Peminjaman(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // ID akan otomatis di-generate
    val nama: String,  // Nama peminjam
    val judulBuku: String,  // Judul buku yang dipinjam
    val tanggalPinjam: String,  // Tanggal peminjaman
    val tanggalKembali: String  // Tanggal pengembalian
)
