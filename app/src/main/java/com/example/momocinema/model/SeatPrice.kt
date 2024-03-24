package com.example.momocinema.model

data class SeatPrice(
    val x: Int,
    val y: Int,
    val price: Int,
    val type: String            // (DEFAULT, VIP) cái này của FILM_PRICE
)
