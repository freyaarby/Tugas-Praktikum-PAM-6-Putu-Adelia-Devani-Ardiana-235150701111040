package com.example.projectpraktikumpam6.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PeminjamanDao {

    @Insert
    suspend fun insert(peminjaman: Peminjaman)

    @Update
    suspend fun update(peminjaman: Peminjaman)

    @Delete
    suspend fun delete(peminjaman: Peminjaman)

    @Query("SELECT * FROM peminjaman_table ORDER BY id ASC")
    fun getAllPeminjaman(): LiveData<List<Peminjaman>>
}
