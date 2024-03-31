package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PAYMENT(
    val id:String,
    val user_id:String,
    val cinema_id:String,
    val date_create:String,
    val amount:String,
):Parcelable

data class PaymentRespone(val PaymentList:List<PAYMENT>)