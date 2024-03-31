package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class COMMENT(
    val id:String,
    val user_id:String,
    val dest_id:String,
    val body:String,
):Parcelable

data class CommentRespone(val CommentList:List<COMMENT>)

