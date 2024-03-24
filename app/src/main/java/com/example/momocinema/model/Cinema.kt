package com.example.momocinema.model

data class Cinema(
    val cinemaRooms: List<CinemaRoom>,
    val name: String,
    val variant: String,
    val logoUrl: String,
)
