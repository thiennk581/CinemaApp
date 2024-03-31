package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TAG(
    val id:String,
    val tag:String,
):Parcelable

data class TagRespone(val TagList:List<TAG>)


