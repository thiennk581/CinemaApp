package com.example.momocinema.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momocinema.AppComponent.detailCinema
import com.example.momocinema.AppComponent.listCinema
import com.example.momocinema.AppComponent.selectDay
import com.example.momocinema.data.Datasource
import com.example.momocinema.model.Cinema
import com.example.momocinema.model.Film
import com.example.momocinema.ui.theme.MomoCinemaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPerformScreen(film: Film) {
    Scaffold(
        topBar = {
            Column {
                SmallTopAppBar(
                    title = { Text(text = film.title, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {            // trở về trang trước
                            OutlinedCard(colors = CardDefaults.outlinedCardColors(Color(0xFF5866C4))) {
                                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = null, tint = Color.White)
                            }
                        }
                    },
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
                    colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xFF234EC6))
                )
                Divider(thickness = 10.dp, color = Color.White)
                selectDay()
            }
        }
    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())) {
            Divider(thickness = 10.dp, color = Color.LightGray, modifier = Modifier.padding( bottom = 10.dp))
            listCinema(listCinema = Datasource().loadCinemas())
            Divider(thickness = 10.dp, color = Color.LightGray)

            var expandedCinemaId by remember { mutableStateOf(0) }
            val listCinemas: List<Cinema> = Datasource().loadCinemas() // TODO: này dựa trên bộ lọc sau khi chọn ngày và loại cinema

            Column {
                for (cinemaId in 0..listCinemas.size-1) {
                    detailCinema(listPerform = Datasource().loadPerforms(), cinema = listCinemas[cinemaId], isExpanded = (cinemaId == expandedCinemaId),  onExpandedButtonClick = {expandedCinemaId = cinemaId})
                    // TODO: listPerform cần đc truyền phù hợp
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun SelectPerformPreview() {
    MomoCinemaTheme {
        SelectPerformScreen(film = Datasource().loadFilms()[0])

    }
}