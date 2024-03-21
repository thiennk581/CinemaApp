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

    }
}