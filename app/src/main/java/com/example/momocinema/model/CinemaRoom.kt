package com.example.momocinema.model

data class CinemaRoom(
    val cinema: Cinema,
    val cinemaLayout: CinemaLayout,
    val type: Int,       // Enum: NORMAL, VIP,...
    val name: String,   // ten phong
)
