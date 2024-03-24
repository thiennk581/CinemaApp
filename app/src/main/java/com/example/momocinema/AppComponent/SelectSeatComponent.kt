package com.example.momocinema.AppComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.R
import com.example.momocinema.model.Perform
import com.example.momocinema.model.SeatPrice
import java.text.SimpleDateFormat

fun formatPrice(price: Int): String {
    return String.format("%,d", price) + "đ"
}

@Composable
fun InfoPerform(perform: Perform) {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(start = 5.dp, end = 10.dp)
                .fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                restrictAgeTag(restrictAge = perform.film.restrictAge)
                Text(text = perform.film.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.width(210.dp))
            }
            Text(text = stringResource(id = R.string.change_perform),
                fontWeight = FontWeight.Bold, color = Color(0xFF234EC6), fontSize = 15.sp,
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .clickable { /* TODO: trở về SelectPerformScreen */ })
        }
        Text(
            text = "${getStringOfTime(perform.startTime)} ~ ${getStringOfTime(perform.endTime)} | ${dayNames[(perform.startTime.day)]}, ${SimpleDateFormat("dd/MM").format(perform.startTime)} | 2D Phụ đề", // TODO: khúc này đưa viewType, translateType (2D 3D Phụ đề Thuyết minh) của Perform vào
            color = Color(0xFF732BF5), modifier = Modifier.padding(start = 10.dp), fontSize = 13.sp
        )
    }
}

@Composable
fun SeatStatus(text: Int, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 7.dp)) {
        Divider( color = color,
            modifier = Modifier
                .padding(end = 3.dp)
                .size(15.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Text(text = stringResource(id = text), fontSize = 12.sp)
    }
}

@Composable
fun displayTotalPrice(totalPrice: Int) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()) {
        Text(text = "Tạm tính")
        Text(text = formatPrice(totalPrice), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Seat(seat: SeatPrice, availableSeat: Boolean, selectingSeat: Boolean, onClick:() -> Unit) {
    val containerColor = if (selectingSeat) Color(0xFF234EC6) else
        if (seat.type == "VIP") Color(0xFFFFCBC3) else Color(0xFFEFDBFE)
    val contentColor = if (selectingSeat) Color.White else
        if (seat.type == "VIP") Color.Red else Color(0xFFEA3FF7)
    val seatName = "${Char('A'.code + seat.y - 1)}${seat.x}"
    Button(
        onClick = onClick,
        enabled = availableSeat,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = Color.LightGray
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .padding(3.dp)
            .size(30.dp)
    ) {
        Text(text = seatName, fontSize = 13.sp, textAlign = TextAlign.Center)
    }
}