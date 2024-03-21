package com.example.momocinema.model

import com.example.momocinema.AppComponent.calculateEndTimePerform

data class Perform(
    val film: Film,
    //val viewType: List<ViewType>,
    //val translateType: List<TranslateType>,
    val startTime: String,              // HH:mm
    val endTime: String = calculateEndTimePerform(startTime, film.duration)
)
