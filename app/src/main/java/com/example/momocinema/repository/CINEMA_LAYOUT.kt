package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA_LAYOUT(
    val id:String,
    val max_x:String,
    val max_y:String,
):Parcelable

data class CinemaLayoutRespone(val CinemaLayoutList:List<CINEMA_LAYOUT>)

