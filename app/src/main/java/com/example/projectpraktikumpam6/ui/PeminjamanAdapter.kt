package com.example.projectpraktikumpam6.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpraktikumpam6.R
import com.example.projectpraktikumpam6.database.Peminjaman

class PeminjamanAdapter(
    private val context: Context,
    private var peminjamanList: List<Peminjaman>,
    private val onDeleteClick: (Int) -> Unit,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<PeminjamanAdapter.ViewHolder>() {

    fun setData(newList: List<Peminjaman>) {
        peminjamanList = newList
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): Peminjaman {
        return peminjamanList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_peminjaman, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = peminjamanList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = peminjamanList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onDetailClick(position) }
        holder.itemView.setOnLongClickListener {
            onDeleteClick(position)
            true
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(peminjaman: Peminjaman) {
            itemView.findViewById<TextView>(R.id.tvNama).text = peminjaman.nama
            itemView.findViewById<TextView>(R.id.tvJudulBuku).text = peminjaman.judulBuku
            itemView.findViewById<TextView>(R.id.tvTanggalPinjam).text = peminjaman.tanggalPinjam
            itemView.findViewById<TextView>(R.id.tvTanggalKembali).text = peminjaman.tanggalKembali
        }
    }
}
