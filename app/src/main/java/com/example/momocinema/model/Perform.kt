package com.example.momocinema.model

import java.sql.Timestamp

data class Perform(
    val listSeat: List<SeatPrice>,
    val film: Film,
    //val viewType: List<ViewType>,
    //val translateType: List<TranslateType>,
    val cinemaRoom: CinemaRoom,
    val startTime: Timestamp,
    val endTime: Timestamp = Timestamp(startTime.time + film.duration*60*1000)      // minute -> milisecond
)
