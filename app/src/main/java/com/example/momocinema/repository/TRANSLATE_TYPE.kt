package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TRANSLATE_TYPE(
    val id:String,
    val translate_type:String,
):Parcelable

data class TranslateTypeRespone(val TranslateTypeList:List<TRANSLATE_TYPE>)
