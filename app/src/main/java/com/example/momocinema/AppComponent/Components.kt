package com.example.momocinema.AppComponent

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.momocinema.R
import com.example.momocinema.model.Film
import kotlin.math.absoluteValue


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
        modifier = Modifier
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
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                BorderStroke(1.dp, SolidColor(Color.White)),
                shape = RoundedCornerShape(12.dp)
            ),
        textAlign = TextAlign.Center
    )
}           // tag 18+ 13+ 16 +P

@Composable
fun filmCard(                                   // TODO
    film: Film,
    purposeTitle: String,
    modifier: Modifier = Modifier
) {
    val alignDetail = if (purposeTitle == "Phim nổi bật") Alignment.CenterHorizontally else Alignment.Start
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(5.dp)
            .size(width = 161.dp, height = 283.dp)
            .clickable { },                                    // TODO

        horizontalAlignment = alignDetail
    ) {
        Box() {
            Image(
                painter = painterResource(id = film.picture), contentDescription = "${film.title} poster", modifier = modifier
                    .padding(bottom = 5.dp)
                    .fillMaxHeight(0.74f)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(9.dp)),
                contentScale = ContentScale.Crop
            )
            restrictAgeTag(restrictAge = film.restrictAge)
        }


        Column(
            modifier = modifier.padding(top = 5.dp),
            horizontalAlignment = alignDetail
        ) {
            if (purposeTitle == "Phim sắp chiếu")
                Text(
                    text = film.releaseDate,
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
                        text = " ${film.ranking}/10 ",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.W400
                    )
                    Text(
                        text = if (film.totalReviews < 1000) "(${film.totalReviews} đánh giá)" else "("+"%.1f".format(film.totalReviews/1000.0) +"K đánh giá)",
                        color = Color(0xFFBEBEBE),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.W400

                    )
                }
            Text(
                text = film.title,                fontWeight = FontWeight(600),

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
                text = film.tag,
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

        Row(modifier = Modifier
            .clickable {  }                         // TODO cần chuyển sang trang có đầy đủ phim hơn
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
}                               // TODO cần xử lý hàm clickable{} của "Xem tất cả >"

@Composable
fun briefFilmList(listFilm: List<Film>, purposeTitle: String, modifier: Modifier = Modifier) {
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
                filmCard(film = film, purposeTitle)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(listFilm: List<Film>, purposeTitle: String, modifier: Modifier = Modifier.fillMaxWidth()) {
    val pagerState = rememberPagerState(initialPage = 2) {
         if (listFilm.size > 5) 5 else listFilm.size
    }
    HorizontalPager(
        beyondBoundsPageCount = 5,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 100.dp),
        modifier = Modifier.height(350.dp)
    ) {page ->
        filmCard(film = listFilm[page], purposeTitle = purposeTitle,
            modifier = Modifier
                .graphicsLayer {

                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue
                    lerp(
                        start = 1f,
                        stop = 1.07f,
                        fraction = 1.07f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                }
        )
    }
}

@Composable
fun ListTrendingNow(listFilm: List<Film> ,purposeTitle: String, modifier: Modifier = Modifier.fillMaxWidth()) {
    Column {
        Text(
            text = purposeTitle,
            fontWeight = FontWeight(600),
            lineHeight = 1.25.sp,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = modifier.padding(top = 20.dp, start = 15.dp)
        )
        CarouselCard(listFilm = listFilm, purposeTitle = purposeTitle)
    }
}

@Composable
fun TextFieldCustom(
    @StringRes label: Int,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange:(String) -> Unit,
    modifier: Modifier = Modifier.size(width = 310.dp, height = 65.dp)
) {

    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label)) },
        placeholder = { Text(text = stringResource(id = label)) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null, tint = Color(0xFF0DD2FA)) },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Composable
fun PasswordTextField(
    @StringRes label: Int,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange:(String) -> Unit,
    idError: Int,
    modifier: Modifier = Modifier.size(width = 310.dp, height = 92.dp)
) {
    var isShowPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label)) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null, tint = Color(0xFF0DD2FA)) },
        placeholder = { Text(text = stringResource(id = label)) },
        keyboardOptions = keyboardOptions,
        trailingIcon = { IconButton(onClick = { isShowPassword = !isShowPassword }) {
            Icon(painter = painterResource(id = if(isShowPassword) R.drawable.view else R.drawable.hide), contentDescription = null, tint = Color(0xFF0DD2FA), modifier = Modifier.size(25.dp))
        }
        },
        visualTransformation = if(isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        isError = (idError > 0),
        supportingText = {
                         @StringRes var warningText = when (idError) {
                             1 -> R.string.idError_1
                             2 -> R.string.idError_2
                             3 -> R.string.idError_3
                             else -> R.string.idError_0
                         }
            Text(text = stringResource(id = warningText), color = Color.Red )
        },
        modifier = modifier
    )
}