package com.example.bookmymovie.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("BookingHistory")
data class BookingHistory(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo("name") var movieName:String,
   @ColumnInfo("movieType") var movieType:String
)
