package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FILM(
    val id:String,
    val title:String,
    val director:String,
    val description:String,
    val picture_url:String,
    val trailer_url:String,
    val release_date:String,
    val language:String,
    val restrict_age:String,
    val duration:String,
):Parcelable

data class FilmRespone(val FilmList:List<FILM>)