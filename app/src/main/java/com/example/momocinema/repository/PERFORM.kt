package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PERFORM(
    val id:String,
    val film_id:String,
    val view_type_id:String,
    val translate_type_id:String,
    val dest_id:String,
    val price:String,
    val start_time:String,
    val end_time:String,
):Parcelable

data class PerformRespone(val PerformList:List<PERFORM>)