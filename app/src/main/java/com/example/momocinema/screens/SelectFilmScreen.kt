package com.example.momocinema.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momocinema.AppComponent.ListTrendingNow
import com.example.momocinema.AppComponent.briefFilmList
import com.example.momocinema.data.Datasource
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable
fun SelectFilmScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        ListTrendingNow(listFilm = Datasource().loadFilms(), purposeTitle = "Phim nổi bật")
        Divider(thickness = 10.dp, color = Color(0xFFEEEEEE))
        briefFilmList(listFilm = Datasource().loadFilms(), "Phim hay đang chiếu")
        Divider(thickness = 10.dp, color = Color(0xFFEEEEEE))
        briefFilmList(listFilm = Datasource().loadFilms(), "Phim sắp chiếu")
    }

}

@Preview(showBackground = true)
@Composable
fun SelectFilmPreview() {
    MomoCinemaTheme {
        SelectFilmScreen()
    }
}