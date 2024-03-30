package com.example.momocinema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.momocinema.data.Datasource
import com.example.momocinema.screens.FilmInfo
import com.example.momocinema.screens.FilmInfoPreview
import com.example.momocinema.screens.SelectFilmScreen
import com.example.momocinema.ui.theme.MomoCinemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MomoCinemaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SelectFilmScreen()
                }
            }
        }
    }
}

val listFilm = Datasource().loadFilms()

val testt = "thien test conflict"

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun GreetingPreview() {
    MomoCinemaTheme {
        //LogoCinema(Cinema("Cinestar", "Đồng Nai", "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170453-637584230934981809.png"))
        // selectDay()
        //listCinema(Datasource().loadCinemas())
        //detailCinema(cinema = Cinema("Cinestar", "Đồng Nai", "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170453-637584230934981809.png"), isExpanded = isExpanded, {isExpanded = !isExpanded})
        //Showtime(perform = Perform(listFilm[0], startTime = "17:20") )
        //SelectSeatScreen(perform = Perform(Datasource().loadSeats(),listFilm[0], startTime = Timestamp.valueOf("2024-03-23 09:00:00.0")),cinema = Cinema(cinemaRooms = listOf<CinemaRoom>(CinemaRoom(1, "ROOM6", CinemaLayout(13,9))) ,"Cinestar", "Đồng Nai", "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170453-637584230934981809.png"))
        //InfoPerform( perform = Perform(listFilm[0], startTime = Timestamp.valueOf("2024-03-23 09:00:00.0")))
        //Seat(seat = SeatPrice(13,9, 100000, "DEFAULT"), availableSeat = true, )
    }
}