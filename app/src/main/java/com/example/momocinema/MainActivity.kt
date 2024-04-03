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
import androidx.navigation.compose.rememberNavController
import com.example.momocinema.data.Datasource
import com.example.momocinema.navigation.CinemaTicketApp
import com.example.momocinema.ui.theme.MomoCinemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MomoCinemaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CinemaTicketApp(navControler = navController)

                }
            }
        }
    }
}

val listFilm = Datasource().loadFilms()








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
        //PaymentScreen(perform = Datasource().loadPerforms()[0])
        //filmComment(Datasource().loadComments()[0])
        //listCommentOfFilm(ranking = Ranking(averageRating = 9.3f, amount = 2200, star12 = 100, star34 = 100, star56 = 500, star78 = 600, star910 = 900), listComment = Datasource().loadComments())
        //ReviewsScreen(filmTitle = "Godzilla x Kong: Đế chế mới", ranking = Ranking(averageRating = 9.3f, amount = 2200, star12 = 100, star34 = 100, star56 = 500, star78 = 600, star910 = 900), listComment = Datasource().loadComments())
    }
}