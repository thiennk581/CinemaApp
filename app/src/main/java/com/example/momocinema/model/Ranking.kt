package com.example.momocinema.model

// a thấy trên web ko có phần số lượng chi tiết như này

data class Ranking(
    val averageRating: Float,
    val amount: Int,
    val star12: Int,
    val star34: Int,
    val star56: Int,
    val star78: Int,
    val star910: Int
)
