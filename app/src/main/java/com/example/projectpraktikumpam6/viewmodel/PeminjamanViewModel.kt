package com.example.projectpraktikumpam6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projectpraktikumpam6.database.PeminjamanDatabase
import com.example.projectpraktikumpam6.database.Peminjaman
import com.example.projectpraktikumpam6.repository.PeminjamanRepository
import kotlinx.coroutines.launch

class PeminjamanViewModel(application: Application) : AndroidViewModel(application) {

    private val peminjamanRepository: PeminjamanRepository
    val allPeminjaman: LiveData<List<Peminjaman>>

    init {
        val peminjamanDao = PeminjamanDatabase.getDatabase(application).peminjamanDao()
        peminjamanRepository = PeminjamanRepository(peminjamanDao)
        allPeminjaman = peminjamanRepository.getAllPeminjaman()
    }

    fun insert(peminjaman: Peminjaman) {
        viewModelScope.launch {
            peminjamanRepository.insert(peminjaman)
        }
    }

    fun update(peminjaman: Peminjaman) {
        viewModelScope.launch {
            peminjamanRepository.update(peminjaman)
        }
    }

    fun delete(peminjaman: Peminjaman) {
        viewModelScope.launch {
            peminjamanRepository.delete(peminjaman)
        }
    }
}
