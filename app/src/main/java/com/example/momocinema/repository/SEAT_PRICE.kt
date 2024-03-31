package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SEAT_PRICE(
    val id:String,
    val perform_id:String,
    val x:String,
    val y:String,
    val price:String,
):Parcelable

data class SeatPriceRespone(val SeatPriceList:List<SEAT_PRICE>)