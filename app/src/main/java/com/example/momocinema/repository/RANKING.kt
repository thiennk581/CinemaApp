package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RANKING(
    val id:String,
    val dest_id:String,
    val ranking:String,
):Parcelable

data class RankingRespone(val RankingList:List<RANKING>)

