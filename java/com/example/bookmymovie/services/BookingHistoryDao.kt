package com.example.bookmymovie.services

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookmymovie.models.BookingHistory

@Dao
interface BookingHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookedMovie(movie : BookingHistory)

    @Query("SELECT * FROM BookingHistory")
    fun getHistory() : LiveData<List<BookingHistory>>
}