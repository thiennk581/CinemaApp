package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA(
    val id:String,
    val variant:String,
    val name:String,
):Parcelable

data class CinemaRespone(val CinemaList:List<CINEMA>)


