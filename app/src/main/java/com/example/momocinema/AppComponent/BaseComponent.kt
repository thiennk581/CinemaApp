/*
 Trang này gồm:
    TopAppBar
    CustomButton: các button login, create account, mua vé...
    restrictAge: nhãn giới hạn độ tuổi
    getStringOfTime: chuyển từ startTime, endTime của suất chiếu sang String
    dayNames: tên của các ngày trong tuần
 */
package com.example.momocinema.AppComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.sql.Timestamp
import java.text.SimpleDateFormat


val dayNames = arrayOf("C.Nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7")

// Timestamp(2024-03-23 14:18:00.0) -> String(14:18)
fun getStringOfTime(time: Timestamp): String = SimpleDateFormat("HH:mm").format(time)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(text: String, onClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = text, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.width(290.dp)) },
        navigationIcon = {
            IconButton(onClick = onClick) {            // trở về trang trước
                OutlinedCard(colors = CardDefaults.outlinedCardColors(Color(0xFF5866C4))) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = null, tint = Color.White)
                }
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color(0xFF234EC6))
    )
}

@Composable
fun CustomButton(actionText: Int, onClick:() -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF234EC6), contentColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(2.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .border(
                BorderStroke(1.dp, SolidColor(Color.Cyan)),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(text = stringResource(id = actionText), fontSize = 17.sp)
    }
}

@Composable
fun restrictAgeTag(restrictAge: Int, modifier: Modifier = Modifier) {
    Text(
        text = when(restrictAge) {
            18 -> "18+"
            16 -> "16+"
            13 -> "13+"
            else -> "P" },
        color = Color.White,
        fontSize = 13.sp,
        modifier = modifier
            .padding(5.dp)
            .height(18.dp)
            .width(34.dp)
            .background(
                color = when (restrictAge) {
                    18 -> Color.Red
                    16 -> Color(0xFFF08650)
                    13 -> Color(0xFFD8BC4F)
                    else -> Color(0xFF5B9817)
                },
                shape = RoundedCornerShape(12.dp,)
            )
            .border(BorderStroke(1.dp, SolidColor(Color.White)), shape = RoundedCornerShape(12.dp)),
        textAlign = TextAlign.Center
    )
}           // tag 18+ 13+ 16 +P