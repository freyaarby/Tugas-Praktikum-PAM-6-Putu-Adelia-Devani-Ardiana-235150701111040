package com.example.projectpraktikumpam6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpraktikumpam6.databinding.ItemPeminjamanBinding
import com.example.projectpraktikumpam6.model.Peminjaman
import com.example.projectpraktikumpam6.DetailActivity

class PeminjamanAdapter(
    private val context: Context,
    private val list: MutableList<Peminjaman>,
    private val onDeleteClick: (Int) -> Unit,
    private val onItemClick: (Int) -> Unit // Menambahkan listener untuk item click
) : RecyclerView.Adapter<PeminjamanAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPeminjamanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Peminjaman, position: Int) {
            binding.tvNama.text = data.nama
            binding.tvJudulBuku.text = data.judulBuku

            binding.btnHapus.setOnClickListener {
                onDeleteClick(position)
            }

            // Menambahkan click listener pada item
            binding.root.setOnClickListener {
                onItemClick(position) // Menggunakan onItemClick yang diterima dari MainActivity
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPeminjamanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }
}
