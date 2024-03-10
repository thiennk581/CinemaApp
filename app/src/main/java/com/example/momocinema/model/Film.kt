package com.example.momocinema.model

import androidx.annotation.DrawableRes

data class Film(
    val title: String,
    @DrawableRes val picture: Int,
    val releaseDate: String,
    val restrictAge: Int,
    val tag: String,        // về sau đổi lại list "chính kịch, hành động, hài,..."
    val ranking: Float,             // trong RANKING chứ ko trong FILM
    val totalReviews: Int,    // tổng số lượt đánh giá (ko nằm trong FILM)
)
