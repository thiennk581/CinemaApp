package com.example.momocinema.model

data class User(
    val name: String,
    val username: String,
    val avt_url: String = "https://i.pinimg.com/736x/f1/0f/f7/f10ff70a7155e5ab666bcdd1b45b726d.jpg",    // nếu ko có avt thì là avt trắng
    val role: Int = Role.USER.ordinal
)
