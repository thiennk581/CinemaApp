package com.example.momocinema.AppComponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.repository.FILM
import kotlin.math.absoluteValue

@Composable
fun numberOfReviews(amount: Int, style: TextStyle, modifier: Modifier = Modifier.padding(bottom = 5.dp)) {
    Text(
        text = if (amount < 1000) "(${amount} đánh giá)" else "("+"%.1f".format(amount/1000.0) +"K đánh giá)",
        color = Color(0xFFBEBEBE),
        style = style,
        fontWeight = FontWeight.W400,
        modifier = modifier
    )
}

@Composable
fun filmCard(film: FILM, listFilmViewModel: SelectFilmViewModel, purposeTitle: String, modifier: Modifier = Modifier) {
    val alignDetail = if (purposeTitle == "Phim nổi bật") Alignment.CenterHorizontally else Alignment.Start
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(5.dp)
            .size(width = 161.dp, height = 283.dp)
            .clickable { },                                    // TODO: nhấp vào chuyển sang FilmInfoScreen
        // anh để hàm clickable trong đây luôn để mỗi lần gọi hàm đỡ phải truyền vào
        horizontalAlignment = alignDetail
    ) {
        Box() {
//            Image(
//                painter = painterResource(id = film.picture), contentDescription = "${film.title} poster", modifier = modifier
//                    .padding(bottom = 5.dp)
//                    .fillMaxHeight(0.74f)
//                    .fillMaxWidth()
//                    .clip(shape = RoundedCornerShape(9.dp)),
//                contentScale = ContentScale.Crop
//            )
            AsyncImage(model = film.picture_url, contentDescription = "${film.title} poster", modifier = modifier
                .padding(bottom = 5.dp)
                .fillMaxHeight(0.74f)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(9.dp)),
                contentScale = ContentScale.Crop)
            restrictAgeTag(restrictAge = film.restrict_age.toInt())
        }


        Column(
            modifier = modifier.padding(top = 5.dp),
            horizontalAlignment = alignDetail
        ) {
            if (purposeTitle == "Phim sắp chiếu")
                Text(
                    text = film.release_date,
                    fontWeight = FontWeight(600),
                    lineHeight = 1.25.sp,
                    fontSize = 13.sp,
                    color = Color.Black,
                    modifier = modifier
                )
            else
                Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
                    .wrapContentSize()
                    .align(alignDetail)) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFE8E1D),
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = " ${listFilmViewModel.averageRankOfFilm(listFilmViewModel.listFilmSelectState.value.listRanking, film = film)}/10 ",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.W400
                    )
                    numberOfReviews(amount = listFilmViewModel.totalRankOfFilm(listFilmViewModel.listFilmSelectState.value.listRanking, film = film), style = MaterialTheme.typography.labelSmall)
                }
            Text(
                text = film.title,
                fontWeight = FontWeight(600),
                lineHeight = 1.25.sp,
                fontSize = 20.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .wrapContentSize()
                    .padding(top = 3.dp)
            )
            Text(
                text = film.title,
                fontSize = 13.sp,
                lineHeight = 1.25.sp,
                color = Color(0xFFBEBEBE),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .offset(x = if (purposeTitle == "Phim nổi bật") 2.dp else 0.dp)
                    .wrapContentSize()
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(listFilmViewModel: SelectFilmViewModel, purposeTitle: String, pagerState: PagerState, modifier: Modifier = Modifier.fillMaxWidth()) {
    val listFilm = listFilmViewModel.listFilmSelectState.value.listFilm
    HorizontalPager(
        beyondBoundsPageCount = 5,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 100.dp),
        modifier = Modifier.height(350.dp)
    ) {page ->
        filmCard(film = listFilm[page],
            listFilmViewModel
            , purposeTitle = purposeTitle,
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue
                    lerp(
                        start = 1f, stop = 1.07f, fraction = 1.07f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListTrendingNow(listFilmViewModel:SelectFilmViewModel,purposeTitle: String, modifier: Modifier = Modifier.fillMaxWidth()) {
    val listFilm = listFilmViewModel.listFilmSelectState.value.listFilm
    val pagerState = rememberPagerState(initialPage = 2) {
        if (listFilm.size > 5) 5 else listFilm.size
    }
    Column {
        Text(
            text = purposeTitle,
            fontWeight = FontWeight(600),
            lineHeight = 1.25.sp,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = modifier.padding(top = 20.dp, start = 15.dp)
        )
        CarouselCard(listFilmViewModel, purposeTitle = purposeTitle, pagerState = pagerState)
    }
}

@Composable
fun titleListFilm(
    purposeTitle: String,                  // Phim hay đang chiếu, Phim sắp chiếu        Xem tất cả
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(start = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = purposeTitle,
            fontWeight = FontWeight(600),
            lineHeight = 1.25.sp,
            fontSize = 18.sp,
            color = Color.Black,
        )

        Row(modifier = Modifier                     // "Xem tất cả >"
            .clickable {  }                         // TODO cần chuyển sang trang có đầy đủ phim hơn "Xem tất cả    >"
        ) {
            Text(
                text = "Xem tất cả",
                fontWeight = FontWeight(600),
                lineHeight = 1.25.sp,
                fontSize = 18.sp,
                color = Color.Black,
            )
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
fun briefFilmList(listFilmViewModel: SelectFilmViewModel, purposeTitle: String, modifier: Modifier = Modifier) {
    val listFilm = listFilmViewModel.listFilmSelectState.value.listFilm
    Column(
        modifier = Modifier.padding(top = 20.dp, bottom = 6.dp)
    ) {
        titleListFilm(purposeTitle = purposeTitle)
        LazyRow(
            contentPadding = PaddingValues(5.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(307.dp),
        ) {
            items(listFilm) { film ->
                filmCard(film = film, listFilmViewModel, purposeTitle)
            }
        }
    }
}