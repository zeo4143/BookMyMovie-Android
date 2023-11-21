package com.example.bookmymovie.helpers

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        synchronized(this) {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "BookMyMovie"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return database!!
        }
    }

    fun closeDatabase() {
        database?.close()
        database = null
    }
}
