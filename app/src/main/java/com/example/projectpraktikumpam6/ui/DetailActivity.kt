package com.example.projectpraktikumpam6.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectpraktikumpam6.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout pakai ViewBinding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent dengan aman
        val nama = intent.getStringExtra("NAMA") ?: "Nama tidak tersedia"
        val judul = intent.getStringExtra("JUDUL") ?: "Judul tidak tersedia"
        val pinjam = intent.getStringExtra("PINJAM") ?: "Tanggal pinjam tidak tersedia"
        val kembali = intent.getStringExtra("KEMBALI") ?: "Tanggal kembali tidak tersedia"

        // Tampilkan data ke TextView
        binding.tvNama.text = "Nama Peminjam: $nama"
        binding.tvJudulBuku.text = "Judul Buku: $judul"
        binding.tvTanggalPinjam.text = "Tanggal Pinjam: $pinjam"
        binding.tvTanggalKembali.text = "Tanggal Kembali: $kembali"
    }
}



