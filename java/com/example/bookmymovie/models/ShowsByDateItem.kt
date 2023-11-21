package com.example.bookmymovie.models

data class ShowsByDateItem(
    val theatreDetails: TheatreDetails,
    val timings: List<Timing>
)