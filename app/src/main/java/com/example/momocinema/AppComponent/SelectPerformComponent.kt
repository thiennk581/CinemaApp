package com.example.momocinema.AppComponent

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.momocinema.model.Cinema
import com.example.momocinema.model.Perform
import java.util.Calendar
import java.util.Date

@Composable
fun DayCard(
    cardId: Int,
    selectedCardId: Int,
    day: Int,
    dayOfWeek: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .padding(horizontal = 7.dp)
        .size(53.dp, 70.dp)
        .clickable(onClick = onClick),
    selected: Boolean = (cardId == selectedCardId),
    color: Color = if (selected) Color(0xFF234EC6) else Color.LightGray,
) {
    Column(
        modifier = modifier
            .size(53.dp, 70.dp)
            .background(color, RoundedCornerShape(9.dp))
            .border(1.dp, color, RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(26.dp)
            .background(Color.White)){
            Text(text = dayOfWeek, color = if (selected) color else Color.Black, fontSize = 13.sp, modifier = Modifier.align(
                Alignment.Center))
        }
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(42.dp)
        ){
            Text(text = day.toString(), color = if (selected) Color.White else Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(
                Alignment.Center))
        }
    }
}

@Composable
fun selectDate(currentTime: Date) {
    val currentDate = currentTime.date
    val currentDay = currentTime.day
    val numberOfDayOfCurrentMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH) + 1
    var selectedCardId by remember { mutableStateOf(0) }

    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .padding(start = 7.dp, end = 7.dp, bottom = 15.dp)) {
        for(cardId in 0..13) {
//          if((currentDay + i) % dayOfCurrentMonth == 0) newMonth = 1
            val day = if (currentDate + cardId <= numberOfDayOfCurrentMonth) (currentDate + cardId ) % numberOfDayOfCurrentMonth else (currentDate + cardId + 1) % numberOfDayOfCurrentMonth
            DayCard(
                cardId = cardId,
                selectedCardId = selectedCardId,
                day = if (day == 0) 1 else day,
                dayOfWeek = if(cardId == 0) "H.nay" else dayNames[(currentDay + cardId) % 7],
                onClick = {selectedCardId = cardId}     // TODO: sau khi chọn ngày ...
            )
            //CustomCard(day = (currentDay + i + newMonth) % dayOfCurrentMonth, dayNames[(currentDayOfWeek+i) % 7], isSelect, { isSelect = !isSelect})
        }
    }
}


@Composable
fun LogoCinema(cinema: Cinema, color: Color = Color.Gray) {
    AsyncImage(model = cinema.logoUrl, contentDescription = null, modifier = Modifier
        .size(50.dp)
        .border(width = 2.dp, color = color, RoundedCornerShape(8.dp)))
}

@Composable
fun listCinema(listCinema: List<Cinema>) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        var selectedCinemaLogoId by remember { mutableStateOf(0) }
        for(cinemaId in 0..7) {
            val isSelected = (selectedCinemaLogoId == cinemaId)
            val selectedColor = if(isSelected) Color(0xFF234EC6) else Color.Gray
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(70.dp, 75.dp)
                    .clickable { selectedCinemaLogoId = cinemaId }
            ) {
                LogoCinema(cinema = listCinema[cinemaId], color = selectedColor)
                Text(text = listCinema[cinemaId].name, fontSize = 13.sp, color = selectedColor, fontWeight = if(isSelected) FontWeight.Bold else null, modifier = Modifier.padding(top = 3.dp))
            }
        }
    }
}

@Composable
fun Showtime(perform: Perform, onClick:() -> Unit) {
    Card(border = BorderStroke(1.dp, Color.LightGray), colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 7.dp)
            .clickable(onClick = onClick)) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(132.dp, 72.dp)
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = getStringOfTime(perform.startTime), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = " ~ ${getStringOfTime(perform.endTime)}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(bottom = 2.dp))
            }
            Text(text = "139/139 Ghế", fontSize = 15.sp, color = Color.Gray)
            // TODO: "139/139 ghế" này a ko biết lấy dữ liệu sao
        }
    }
}

@Composable
fun detailCinema(listPerform: List<Perform>, cinema: Cinema, isExpanded: Boolean, onExpandedButtonClick:() -> Unit, modifier: Modifier = Modifier) {
    val extraPadding by animateDpAsState(               // cho phần mở rộng, thu hẹp Showtime
        targetValue = if (isExpanded) 20.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Column (modifier = Modifier
        .padding(horizontal = 10.dp)
        .fillMaxWidth()){
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 3.dp)
            .fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LogoCinema(cinema = cinema)
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = "${cinema.name} ${cinema.variant}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = cinema.variant, fontSize = 14.sp, color = Color.Gray)
                }
            }
            IconButton(onClick = onExpandedButtonClick) {
                Icon(imageVector = if (!isExpanded) Icons.Sharp.KeyboardArrowDown else Icons.Sharp.KeyboardArrowUp, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        }
        if (isExpanded) Column(Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
            Text(text = "2D Phụ đề" /* TODO: truyền viewType, translateType */, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp, start = 3.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(255.dp)
            ) {
                items(listPerform) {item   ->
                    Showtime(perform = item, onClick = { /* TODO: chuyển sang trang chọn ghế */})
                }
            }
        }
    }
}