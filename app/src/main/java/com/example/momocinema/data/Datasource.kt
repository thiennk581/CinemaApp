package com.example.momocinema.data

import com.example.momocinema.R
import com.example.momocinema.model.Film

class Datasource() {
    fun loadFilms(): List<Film> {
        return listOf<Film>(
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "08 Th 03", restrictAge = 16, tag = "Chính kịch, Hành động, Hài", ranking = 9.1f, totalReviews = 36000),
            Film(title = "Đào, phở và piano", picture = R.drawable.mai_poster, releaseDate = "14 Th 06", restrictAge = 18, tag = "Chính kịch", ranking = 8.1f, totalReviews = 360),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "20 Th 10", restrictAge = 10, tag = "Chính kịch, Hành động, Hài", ranking = 1.1f, totalReviews = 30),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "11 Th 12", restrictAge = 13, tag = "Hài", ranking = 9.1f, totalReviews = 360000),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "01 Th 01", restrictAge = 18, tag = "Chính kịch, Hành động", ranking = 9.1f, totalReviews = 360000),
            Film(title = "MAI", picture = R.drawable.mai_poster, releaseDate = "30 Th 05", restrictAge = 1, tag = "Chính kịch, Hành động, Hài", ranking = 9.1f, totalReviews = 36000)

        )
    }
}