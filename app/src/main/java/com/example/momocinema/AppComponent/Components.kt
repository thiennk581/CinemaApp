package com.example.momocinema.AppComponent

import androidx.annotation.StringRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.example.momocinema.R
import com.example.momocinema.model.Cast
import com.example.momocinema.model.Cinema
import com.example.momocinema.model.Film
import com.example.momocinema.model.Perform
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import kotlin.math.absoluteValue

// Timestamp(2024-03-23 14:18:00.0) -> String(14:18)
fun getStringOfTime(time: Timestamp): String = SimpleDateFormat("HH:mm").format(time)


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
            .border(
                BorderStroke(1.dp, SolidColor(Color.White)),
                shape = RoundedCornerShape(12.dp)
            ),
        textAlign = TextAlign.Center
    )
}           // tag 18+ 13+ 16 +P

@Composable
fun numberOfReviews(amount: Int, style: TextStyle) {
    Text(
        text = if (amount < 1000) "(${amount} đánh giá)" else "("+"%.1f".format(amount/1000.0) +"K đánh giá)",
        color = Color(0xFFBEBEBE),
        style = style,
        fontWeight = FontWeight.W400
    )
}

@Composable
fun filmCard(
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
            .clickable { },                                    // TODO: nhấp vào chuyển sang FilmInfoScreen
                // anh để hàm clickable trong đây luôn để mỗi lần gọi hàm đỡ phải truyền vào
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
                        text = " ${film.ranking.averageRating}/10 ",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.W400
                    )
                    numberOfReviews(amount = film.ranking.amount, style = MaterialTheme.typography.labelSmall)
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

@Composable
fun detailRating(film: Film) {
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .fillMaxWidth()


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 17.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Absolute.Center) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFE8E1D), modifier = Modifier.size(30.dp))
                    Text(text = film.ranking.averageRating.toString(), fontSize = 30.sp, fontWeight = FontWeight.Bold, lineHeight = 30.sp)
                    Text(
                        text = "/10",
                        fontWeight = FontWeight(450),
                        fontSize = 15.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 16.25.sp,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 5.dp, bottom = 5.dp)
                            .align(Alignment.Bottom)
                    )
                }
                numberOfReviews(amount = film.ranking.amount, style = MaterialTheme.typography.labelLarge)
            }
            Column {
                determinateProgress(progress = (film.ranking.star910.toFloat() / film.ranking.amount), star = 9)
                determinateProgress(progress = (film.ranking.star78.toFloat() / film.ranking.amount), star = 7)
                determinateProgress(progress = (film.ranking.star56.toFloat() / film.ranking.amount), star = 5)
                determinateProgress(progress = (film.ranking.star34.toFloat() / film.ranking.amount), star = 3)
                determinateProgress(progress = (film.ranking.star12.toFloat() / film.ranking.amount), star = 1)

            }
        }
    }
}

@Composable
fun determinateProgress(progress: Float, star: Int) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 5.dp)) {
        Text(text = "$star-${star+1}", modifier = Modifier.width(35.dp))
        Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.LightGray, modifier = Modifier
            .padding(end = 10.dp)
            .size(17.dp))
        LinearProgressIndicator(
            progress = progress,
            color = Color(0xFFFE8E1D),
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .size(145.dp, 7.dp)
                .clip(shape = RoundedCornerShape(100.dp))
        )
    }
}


@Composable
fun firstInfo(film: Film) {
    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
        Image(painter = painterResource(id = film.picture), contentDescription = null, modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .clip(shape = RoundedCornerShape(8.dp)))
        Column(modifier = Modifier.padding(start = 5.dp, end = 10.dp)) {
            Text(
                text = film.title,
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 3.dp, start = 5.dp)
            )
            Text(
                text = film.tag,
                fontWeight = FontWeight(450),
                fontSize = 13.sp,
                color = Color(0xFF797979),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.25.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
            )
            Row {
                restrictAgeTag(restrictAge = film.restrictAge)
                Text(
                    text = stringResource(
                        id = when (film.restrictAge) {
                            18 -> R.string.over_18
                            16 -> R.string.over_16
                            else -> R.string.all_age
                        }
                    ),
                    fontWeight = FontWeight(450), fontSize = 13.sp, color = Color(0xFF797979), lineHeight = 16.25.sp,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 5.dp, start = 5.dp)
                )
            }
        }

    }
}
@Composable
fun secondInfo(title: Int, detail: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight(450),
            fontSize = 13.sp,
            color = Color(0xFF797979),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.25.sp,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
        )

        Text(
            text = detail,
            fontWeight = FontWeight(600),
            fontSize = 15.sp,
            color = Color.Black,
            maxLines = 2,
            lineHeight = 17.sp,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 3.dp, start = 5.dp)
                .width(88.dp)
        )
    }
}

@Composable
fun castInfo(cast: Cast) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(120.dp)) {
        AsyncImage(
            model = cast.pictureUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(120.dp, 170.dp)
                .clip(RoundedCornerShape(7.dp))
        )
        Text(text = cast.name, textAlign = TextAlign.Center, lineHeight = 17.sp)
        Text(text = cast.characterName, textAlign = TextAlign.Center, lineHeight = 17.sp, color = Color(0xFF949494), modifier = Modifier.padding(bottom = 17.dp))
    }
}

@Composable
fun CustomButton(content: Int, onClick:() -> Unit, modifier: Modifier = Modifier) {
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
        Text(text = stringResource(id = content), fontSize = 20.sp)
    }
}

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
        .clickable(onClick = onClick), // TODO: truyền hàm xử lý khi chọn và chọn cái khác
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
            Text(text = dayOfWeek, color = if (selected) color else Color.Black, fontSize = 13.sp, modifier = Modifier.align(Alignment.Center))
        }
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(42.dp)
        ){
            Text(text = day.toString(), color = if (selected) Color.White else Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
        }
    }

}

@Composable
fun selectDay(currentTime: Date) {
    val dayNames = arrayOf("C.Nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7")
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
                onClick = {selectedCardId = cardId}     // TODO: đây là hàm chọn
            )
            //CustomCard(day = (currentDay + i + newMonth) % dayOfCurrentMonth, dayNames[(currentDayOfWeek+i) % 7], isSelect, { isSelect = !isSelect})
        }
    }
}

// select typeCinema
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
    val extraPadding by animateDpAsState(
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
            Text(text = "2D Phụ đề", fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp, start = 3.dp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title: String, onClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.width(290.dp))},
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

fun formatPrice(price: Int): String {
    return String.format("%,d", price) + "đ"
}