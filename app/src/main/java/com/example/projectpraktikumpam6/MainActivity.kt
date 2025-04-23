package com.example.projectpraktikumpam6

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectpraktikumpam6.databinding.ActivityMainBinding
import com.example.projectpraktikumpam6.model.Peminjaman

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val daftarPeminjaman = ArrayList<Peminjaman>()
    private lateinit var adapter: PeminjamanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        tambahDummyData()

        binding.fabAdd.setOnClickListener {
            showInputDialog()
        }
    }

    private fun setupRecyclerView() {
        // Membuat adapter dan mengirimkan onItemClick
        adapter = PeminjamanAdapter(this, daftarPeminjaman, { position ->
            // Logika untuk delete item jika di klik
            daftarPeminjaman.removeAt(position)
            adapter.notifyItemRemoved(position)
        }, { position ->
            // Logika untuk handle item click
            val peminjaman = daftarPeminjaman[position]
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("NAMA", peminjaman.nama)
                putExtra("JUDUL", peminjaman.judulBuku)
                putExtra("PINJAM", peminjaman.tanggalPinjam)
                putExtra("KEMBALI", peminjaman.tanggalKembali)
            }
            startActivity(intent)
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun tambahDummyData() {
        daftarPeminjaman.add(Peminjaman("Ayu Wulandari", "Algoritma Dasar", "10 April 2025", "20 April 2025"))
        daftarPeminjaman.add(Peminjaman("Raka Satria", "Basis Data", "12 April 2025", "25 April 2025"))
        adapter.notifyDataSetChanged()
    }

    private fun showInputDialog() {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val namaInput = EditText(this).apply {
            hint = "Nama Peminjam"
            inputType = InputType.TYPE_CLASS_TEXT
        }

        val judulInput = EditText(this).apply {
            hint = "Judul Buku"
            inputType = InputType.TYPE_CLASS_TEXT
        }

        val pinjamInput = EditText(this).apply {
            hint = "Tanggal Pinjam (cth: 23 April 2025)"
            inputType = InputType.TYPE_CLASS_TEXT
        }

        val kembaliInput = EditText(this).apply {
            hint = "Tanggal Kembali (cth: 30 April 2025)"
            inputType = InputType.TYPE_CLASS_TEXT
        }

        layout.apply {
            addView(namaInput)
            addView(judulInput)
            addView(pinjamInput)
            addView(kembaliInput)
        }

        AlertDialog.Builder(this)
            .setTitle("Tambah Peminjaman")
            .setView(layout)
            .setPositiveButton("Tambah") { _, _ ->
                val nama = namaInput.text.toString()
                val judul = judulInput.text.toString()
                val pinjam = pinjamInput.text.toString()
                val kembali = kembaliInput.text.toString()

                // Pastikan semua input tidak kosong
                if (nama.isNotEmpty() && judul.isNotEmpty() && pinjam.isNotEmpty() && kembali.isNotEmpty()) {
                    val dataBaru = Peminjaman(nama, judul, pinjam, kembali)
                    daftarPeminjaman.add(dataBaru)
                    adapter.notifyItemInserted(daftarPeminjaman.size - 1)
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
