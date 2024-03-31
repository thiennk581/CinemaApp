package com.example.momocinema.AppComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.R
import com.example.momocinema.model.Perform
import java.text.SimpleDateFormat

@Composable
fun detailPerform(perform: Perform) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.padding(bottom = 5.dp, top = 10.dp)) {
            Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null, tint = Color(0xFF234EC6))
            Text(text = "${dayNames[(perform.startTime.day)]}, ${SimpleDateFormat("dd/MM").format(perform.startTime)} | ${getStringOfTime(perform.startTime)} ~ ${getStringOfTime(perform.endTime)}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 7.dp))
        }
        Row(modifier = Modifier.padding(bottom = 5.dp)) {
            Icon(imageVector = Icons.Outlined.Place, contentDescription = null, tint = Color(0xFF234EC6))
            Text(text = "Rạp " + perform.cinemaRoom.cinema.name + " " + perform.cinemaRoom.cinema.variant, color = Color.Gray, modifier = Modifier.padding(start = 7.dp))
        }
        Row(modifier = Modifier.padding(bottom = 5.dp, start = 2.dp)) {
            Icon(painter = painterResource(id = R.drawable.cinema_icon), tint = Color(0xFF234EC6), contentDescription = null, modifier = Modifier.size(21.dp))
            Text(text = "Phòng chiếu " + perform.cinemaRoom.name, color = Color.Gray, modifier = Modifier.padding(start = 7.dp))
        }
        Row(modifier = Modifier.padding(bottom = 15.dp, start = 2.dp)) {
            Icon(painter = painterResource(id = R.drawable.seat_icon), tint = Color(0xFF234EC6), contentDescription = null, modifier = Modifier.size(20.dp))
            Text(text = "G06, G07, G05, G04, H05, H04, H06, H07", color = Color.Gray, modifier = Modifier.padding(start = 7.dp))
            // TODO: xử lý các ghế đã được chọn (tối đa 8)
        }
    }
}

@Composable
fun UserInfo(fullname: String, email: String) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Thông tin người nhận", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = fullname + " - " + email, color = Color.Gray)
    }
}

@Composable
fun detailPrice(amountSelectedSeat: Int, seatType: String, seatPrice: Int) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Chi tiết đơn hàng", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 8.dp, bottom = 5.dp)
                .fillMaxWidth()
        ) {
            Text(text = if (seatType == "VIP") "Ghế VIP" else "Ghế thường", color = Color.Gray)
            Text(text = formatPrice(seatPrice) + " x" + amountSelectedSeat.toString(), color = Color.Gray )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tổng tiền", fontSize = 19.sp)
            Text(text = formatPrice(amountSelectedSeat * seatPrice), fontSize = 23.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PaymentMethod(selected: Boolean, method: String, onClick:() -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        val logo = when (method) {
            "Momo" -> R.drawable.momo_logo
            "Internet Banking" -> R.drawable.internet_banking_logo
            else -> R.drawable.zalopay_logo
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = logo), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.padding(end = 6.dp).size(40.dp))
            Text(text = method)
        }
        RadioButton(selected = selected, onClick = onClick)
    }
}