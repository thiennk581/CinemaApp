package com.example.momocinema.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.CustomButton
import com.example.momocinema.AppComponent.CustomTopAppBar
import com.example.momocinema.AppComponent.PaymentMethod
import com.example.momocinema.AppComponent.UserInfo
import com.example.momocinema.AppComponent.detailPerform
import com.example.momocinema.AppComponent.detailPrice
import com.example.momocinema.AppComponent.firstInfo
import com.example.momocinema.R
import com.example.momocinema.data.Datasource
import com.example.momocinema.model.Perform
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable
fun PaymentScreen(perform: Perform) {
    val methods = listOf("Momo", "Internet Banking", "ZaloPay")
    var selectedPaymentMethod by remember {
        mutableStateOf("Momo")
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Thông tin thanh toán", onClick = {/* TODO: về SelectSeatScreen */}) },
        bottomBar = { CustomButton(actionText =  R.string.pay_button, onClick = { /*TODO: thanh toán*/ }) }
    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
        ) {
            firstInfo(film = perform.film)
            detailPerform(perform = perform)
            Divider(thickness = 1.dp)
            detailPrice(amountSelectedSeat = 8, seatType = "VIP", seatPrice = 120000)
            Divider(thickness = 1.dp)
            UserInfo(fullname = "Nguyen Van A", email = "nguyenvana@gmail.com")
            Divider(thickness = 1.dp)

            // Payment Methods
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "Phương thức thanh toán", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                methods.forEach{method->
                    PaymentMethod(selected = selectedPaymentMethod == method, method = method, onClick = {selectedPaymentMethod = method})
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun PaymentPreview() {
    MomoCinemaTheme {
        PaymentScreen(perform = Datasource().loadPerforms()[0])
    }
}