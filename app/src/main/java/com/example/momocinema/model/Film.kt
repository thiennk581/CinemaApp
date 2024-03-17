package com.example.momocinema.model

import androidx.annotation.DrawableRes

data class Film(
    val title: String,
    @DrawableRes val picture: Int,
    val releaseDate: String,
    val duration: Int,
    val restrictAge: Int,
    val tag: String,        // về sau đổi lại list "chính kịch, hành động, hài,..."
    val ranking: Ranking,             // trong RANKING chứ ko trong FILM
    val language: String,
    val description: String,
    val cast: List<Cast>
)
