package com.example.projectpraktikumpam6.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectpraktikumpam6.R
import com.example.projectpraktikumpam6.databinding.ActivityMainBinding
import com.example.projectpraktikumpam6.helper.DateHelper
import com.example.projectpraktikumpam6.helper.ValidationHelper
import com.example.projectpraktikumpam6.database.Peminjaman
import com.example.projectpraktikumpam6.viewmodel.PeminjamanViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PeminjamanAdapter
    private val peminjamanViewModel: PeminjamanViewModel by viewModels()

    companion object {
        const val EXTRA_NAMA = "NAMA"
        const val EXTRA_JUDUL = "JUDUL"
        const val EXTRA_PINJAM = "PINJAM"
        const val EXTRA_KEMBALI = "KEMBALI"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()

        binding.fabAdd.setOnClickListener {
            showInputDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = PeminjamanAdapter(
            this,
            emptyList(),
            onDeleteClick = { position ->
                val peminjaman = adapter.getItemAtPosition(position)
                peminjamanViewModel.delete(peminjaman)
                Toast.makeText(this, "Peminjaman dihapus", Toast.LENGTH_SHORT).show()
            },
            onDetailClick = { position ->
                val peminjaman = adapter.getItemAtPosition(position)
                Intent(this, DetailActivity::class.java).apply {
                    putExtra(EXTRA_NAMA, peminjaman.nama)
                    putExtra(EXTRA_JUDUL, peminjaman.judulBuku)
                    putExtra(EXTRA_PINJAM, peminjaman.tanggalPinjam)
                    putExtra(EXTRA_KEMBALI, peminjaman.tanggalKembali)
                    startActivity(this)
                }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun observeData() {
        peminjamanViewModel.allPeminjaman.observe(this) { daftarPeminjaman ->
            adapter.setData(daftarPeminjaman)
        }
    }

    private fun showInputDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_input, null)
        val etNama = dialogView.findViewById<EditText>(R.id.etNama)
        val etJudul = dialogView.findViewById<EditText>(R.id.etJudul)
        val etPinjam = dialogView.findViewById<EditText>(R.id.etPinjam)
        val etKembali = dialogView.findViewById<EditText>(R.id.etKembali)

        AlertDialog.Builder(this)
            .setTitle("Tambah Peminjaman")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val nama = etNama.text.toString()
                val judul = etJudul.text.toString()
                val pinjam = etPinjam.text.toString()
                val kembali = etKembali.text.toString()

                if (!ValidationHelper.isValidNama(nama) ||
                    !ValidationHelper.isValidTanggal(pinjam) ||
                    !ValidationHelper.isValidTanggal(kembali)
                ) {
                    Toast.makeText(this, "Nama atau tanggal tidak valid", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val formattedPinjam = DateHelper.parseTanggal(pinjam)
                val formattedKembali = DateHelper.parseTanggal(kembali)

                if (formattedPinjam == null || formattedKembali == null) {
                    Toast.makeText(this, "Format tanggal salah", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                if (formattedKembali.before(formattedPinjam)) {
                    Toast.makeText(this, "Tanggal kembali harus setelah tanggal pinjam", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val peminjaman = Peminjaman(0, nama, judul, pinjam, kembali)
                peminjamanViewModel.insert(peminjaman)
                Toast.makeText(this, "Data disimpan", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .create()
            .show()
    }
}

