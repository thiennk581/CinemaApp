package com.example.momocinema.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momocinema.AppComponent.ListTrendingNow
import com.example.momocinema.AppComponent.briefFilmList
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.data.Datasource
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable
fun SelectFilmScreen(listFilmViewModel:SelectFilmViewModel,modifier: Modifier = Modifier, navigateToAnotherScreen:()->Unit) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        ListTrendingNow(listFilmViewModel, purposeTitle = "Phim nổi bật")   //               // TODO: truyền listFilm phù hợp vào

        Divider(thickness = 10.dp, color = Color(0xFFEEEEEE))

        briefFilmList(listFilmViewModel, purposeTitle = "Phim hay đang chiếu")               // TODO: truyền listFilm phù hợp vào

        Divider(thickness = 10.dp, color = Color(0xFFEEEEEE))

        briefFilmList(listFilmViewModel, purposeTitle = "Phim sắp chiếu")                // TODO: truyền listFilm phù hợp vào
    }

}

//@Preview(showBackground = true)
//@Composable
//fun SelectFilmPreview() {
//    MomoCinemaTheme {
//        SelectFilmScreen()
//    }
//}