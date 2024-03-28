package com.example.momocinema.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momocinema.AppComponent.CustomButton
import com.example.momocinema.AppComponent.CustomTopAppBar
import com.example.momocinema.AppComponent.InfoPerform
import com.example.momocinema.AppComponent.Seat
import com.example.momocinema.AppComponent.SeatStatus
import com.example.momocinema.AppComponent.displayTotalPrice
import com.example.momocinema.R
import com.example.momocinema.data.Datasource
import com.example.momocinema.listFilm
import com.example.momocinema.model.Perform
import com.example.momocinema.ui.theme.MomoCinemaTheme
import java.sql.Timestamp

@Composable
fun SelectSeatScreen(perform: Perform) {
    val cinemaLayoutMaxX = perform.cinemaRoom.cinemaLayout.maxX
    val cinemaLayoutMaxY = perform.cinemaRoom.cinemaLayout.maxY
    // //TODO: truyền maxY cho thích hợp
    var isSelected = MutableList((cinemaLayoutMaxX+1) * (cinemaLayoutMaxY+1)) { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    text = perform.cinemaRoom.cinema.name + " " + perform.cinemaRoom.cinema.variant,
                    onClick = { /* TODO: trở về SelectPerformScreen */ })
                Image(painter = painterResource(id = R.drawable.screen_in_cinema), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.fillMaxWidth())
            } },
        bottomBar = {
            Column {
                Divider(thickness = 1.dp, modifier = Modifier.padding(top = 7.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp).fillMaxWidth()
                ) {
                    SeatStatus(text = R.string.selectedSeat, color = Color.LightGray)
                    SeatStatus(text = R.string.selectingSeat, color = Color(0xFF234EC6))
                    SeatStatus(text = R.string.normalSeat, color = Color(0xFFEFDBFE))
                    SeatStatus(text = R.string.VIPSeat, color = Color(0xFFFFCBC3))
                }
                Divider(thickness = 10.dp, color = Color.LightGray, modifier = Modifier.padding(bottom = 12.dp))
                InfoPerform(perform = perform)
                displayTotalPrice(totalPrice = 12345678)      // TODO: tính toán tiền xong đưa vào
                CustomButton(actionText =  R.string.continue_button, onClick = { /*TODO: chuyển sang thanh toán*/ })
            }
        }

    ) {it->
        Column(modifier = Modifier
            .padding(it)
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(cinemaLayoutMaxY),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.size(width = (cinemaLayoutMaxX * 35).dp, height = (cinemaLayoutMaxY * 35).dp)
            ){
                items(perform.listSeat) {seat->
                    Seat(seat = seat,
                        availableSeat = true,   // ghế đã có ng đặt hay chưa
                        selectingSeat = isSelected[(seat.y-1) * cinemaLayoutMaxX + seat.x].value,
                        // TODO: e ko muốn đưa state vào thì chỉnh selectingSeat
                        // chỗ này bị bug khi chạy trên device anh cũng chả biết fix sao:vv
                        onClick = {     //TODO hàm khi chọn ghế
                            isSelected[(seat.y-1) * cinemaLayoutMaxX + seat.x].value = !isSelected[(seat.y-1) * cinemaLayoutMaxX + seat.x].value
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun SelectSeatPreview() {
    MomoCinemaTheme {
        SelectSeatScreen(
            perform = Perform(Datasource().loadSeats(), listFilm[0], startTime = Timestamp.valueOf("2024-03-23 09:00:00.0"), cinemaRoom = Datasource().loadCinemaRooms()[0]),
           )

    }
}