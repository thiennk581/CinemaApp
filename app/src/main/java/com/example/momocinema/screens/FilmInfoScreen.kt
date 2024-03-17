package com.example.momocinema.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.CustomButton
import com.example.momocinema.AppComponent.castInfo
import com.example.momocinema.AppComponent.detailRating
import com.example.momocinema.AppComponent.firstInfo
import com.example.momocinema.AppComponent.secondInfo
import com.example.momocinema.R
import com.example.momocinema.data.Datasource
import com.example.momocinema.model.Film
import com.example.momocinema.ui.theme.MomoCinemaTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FilmInfo(film: Film) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = film.title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {            // trở về trang trước
                        OutlinedCard { Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = null) }
                    }
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            )
        },
        bottomBar = { CustomButton(content = R.string.buy_button, onClick = {/* TODO */}) }

    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
        ) {
            Image(painter = painterResource(id = R.drawable.panda), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
                .height(222.dp)
                .fillMaxWidth()
                .padding(bottom = 10.dp))

            firstInfo(film = film)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp) ,horizontalArrangement = Arrangement.SpaceEvenly) {
                secondInfo(title = R.string.release_date, detail = film.releaseDate)
                Divider(thickness = 1.dp, color = Color(0xFFC8C8C8), modifier = Modifier
                    .height(48.dp)
                    .width(1.dp)
                    .offset(y = 5.dp))
                secondInfo(title = R.string.duration, detail = "${film.duration} min")
                Divider(thickness = 1.dp, color = Color(0xFFC8C8C8), modifier = Modifier
                    .padding(start = 5.dp)
                    .height(48.dp)
                    .width(1.dp)
                    .offset(y = 5.dp))
                secondInfo(title = R.string.language, detail = film.language)
            }

            detailRating(film)

            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))
            // description
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(vertical = 17.dp, horizontal = 10.dp)) {
                Text(text = "Nội dung phim", fontWeight = FontWeight(600), fontSize = 19.sp,)
                Text(text = film.description, modifier = Modifier.padding(top = 7.dp))
            }
            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))
            // cast & Crew
            // phần clickable (hiện các phim mà cast đã tham gia) chưa cần thiết, qua giai đoạn 2 làm sau
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 15.dp)) {
                Text(text = "Đạo diễn & Diễn viên", fontWeight = FontWeight(600), fontSize = 19.sp, modifier = Modifier.padding(bottom = 10.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(film.cast) { cast ->
                        castInfo(cast = cast)
                    }
                }
            }
            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilmInfoPreview() {
    MomoCinemaTheme {
        FilmInfo(Datasource().loadFilms()[0])
    }
}