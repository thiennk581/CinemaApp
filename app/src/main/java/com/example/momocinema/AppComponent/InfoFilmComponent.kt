package com.example.momocinema.AppComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.momocinema.R
import com.example.momocinema.model.Cast
import com.example.momocinema.model.Film

@Composable
fun firstInfo(film: Film) {
    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
//        Image(painter = painterResource(id = film.picture), contentDescription = null, modifier = Modifier
//            .width(120.dp)
//            .height(160.dp)
//            .clip(shape = RoundedCornerShape(8.dp)))
        AsyncImage(model = film.pictureUrl, contentDescription = null, modifier = Modifier
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