package com.example.momocinema.model

data class Comment(
    val user: User,
    val body: String,
    val ranking: Int,
)
