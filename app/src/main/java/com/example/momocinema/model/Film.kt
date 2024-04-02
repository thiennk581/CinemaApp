package com.example.momocinema.model

import java.sql.Timestamp

data class Film(
    val title: String,
    val pictureUrl : String,
    val releaseDate: Timestamp,
    val duration: Int,                  // minute
    val restrictAge: Int,
    val tag: String,        // về sau đổi lại list<Tag> "chính kịch, hành động, hài,..."
    val ranking: Ranking,             // trong RANKING chứ ko trong FILM
    val language: String,
    val description: String,
    val listComment: List<Comment>,     // film gồm nhiều comment
    val cast: List<Cast>
)
