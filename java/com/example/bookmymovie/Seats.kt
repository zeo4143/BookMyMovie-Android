package com.example.bookmymovie

data class Seats(
    val row: Int,
    val column: Int,
    var isSelected: Boolean = false
)

