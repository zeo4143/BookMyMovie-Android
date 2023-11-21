package com.example.bookmymovie.helpers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookmymovie.models.BookingHistory
import com.example.bookmymovie.services.BookingHistoryDao

@Database(entities = [BookingHistory::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookingHistoryDao(): BookingHistoryDao


}