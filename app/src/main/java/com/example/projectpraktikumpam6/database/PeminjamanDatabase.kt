package com.example.projectpraktikumpam6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Peminjaman::class], version = 1, exportSchema = false)
abstract class PeminjamanDatabase : RoomDatabase() {

    abstract fun peminjamanDao(): PeminjamanDao

    companion object {
        @Volatile
        private var INSTANCE: PeminjamanDatabase? = null

        fun getDatabase(context: Context): PeminjamanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeminjamanDatabase::class.java,
                    "peminjaman_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
