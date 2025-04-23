package com.example.projectpraktikumpam6

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

        // Ambil data dari intent
        val nama = intent.getStringExtra("NAMA")
        val judul = intent.getStringExtra("JUDUL")
        val pinjam = intent.getStringExtra("PINJAM")
        val kembali = intent.getStringExtra("KEMBALI")

        // Tampilkan ke TextView
        binding.tvNama.text = "Nama Peminjam: $nama"
        binding.tvJudulBuku.text = "Judul Buku: $judul"
        binding.tvTanggalPinjam.text = "Tanggal Pinjam: $pinjam"
        binding.tvTanggalKembali.text = "Tanggal Kembali: $kembali"
    }
}



