package com.example.momocinema.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.CustomTopAppBar
import com.example.momocinema.AppComponent.createCommentTextButton
import com.example.momocinema.AppComponent.detailRating
import com.example.momocinema.AppComponent.filmComment
import com.example.momocinema.data.Datasource
import com.example.momocinema.model.Comment
import com.example.momocinema.model.Ranking
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable
fun ReviewsScreen(filmTitle: String, ranking: Ranking, listComment: List<Comment>) {
    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(text = "Đánh giá", onClick = { /* TODO: trở về trang FilmInfoScreen*/ })
                Row(modifier = Modifier.padding(7.dp)) {
                    Text(text = "Đánh giá của ", fontSize = 13.sp)
                    Text(text = filmTitle, fontSize = 13.sp, color = Color(0xFF234EC6), fontWeight = FontWeight(500))
                }
            }
        }
    ) {it ->
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Column(modifier = Modifier.background(Color(0xFFF7F7F7))) {
                    Divider(thickness = 1.dp, modifier = Modifier.padding(bottom = 10.dp))
                    Text(text = "Tổng quan đánh giá", fontWeight = FontWeight(500), modifier = Modifier.padding(start = 10.dp))
                    detailRating(ranking)
                    Row(modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Danh sách bài viết", fontWeight = FontWeight(500))
                        createCommentTextButton(text = "Viết đánh giá")
                    }
                    Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                }
            }
            items(listComment) {comment ->
                filmComment(comment = comment)
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun ReviewsPreview() {
    MomoCinemaTheme {
        ReviewsScreen(
            filmTitle = "Godzilla x Kong: Đế chế mới",
            ranking = Ranking(averageRating = 9.3f, amount = 2200, star12 = 100, star34 = 100, star56 = 500, star78 = 600, star910 = 900),
            listComment = Datasource().loadComments()
        )
    }
}