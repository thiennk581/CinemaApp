package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CINEMA_ROOM(
    val id:String,
    val cinema_id:String,
    val cinema_layout_id:String,
    val type:String,
    val name:String,
):Parcelable

data class CinemaRoomRespone(val CinemaRoomList:List<CINEMA_ROOM>)

