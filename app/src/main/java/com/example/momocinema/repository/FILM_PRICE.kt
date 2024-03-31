package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FILM_PRICE(
    val id:String,
    val film_id:String,
    val type:String,
    val price:String,
):Parcelable

data class FilmPriceRespone(val FilmPriceList:List<FILM_PRICE>)

