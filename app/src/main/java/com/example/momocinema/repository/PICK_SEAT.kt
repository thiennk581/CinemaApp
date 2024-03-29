package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PICK_SEAT(
    val id:String,
    val user_id:String,
    val perform_id:String,
    val x:String,
    val y:String,
):Parcelable

data class PickSeatRespone(val PickSeatList:List<PICK_SEAT>)
