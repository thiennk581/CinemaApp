package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA_MANAGER(
    val id:String,
    val cinema_id:String,
    val user_id:String,
):Parcelable

data class CinemaManagerRespone(val CinemaManagerList:List<CINEMA_MANAGER>)


