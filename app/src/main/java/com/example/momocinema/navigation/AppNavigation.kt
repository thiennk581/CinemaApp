package com.example.momocinema.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.screens.FilmInfo
import com.example.momocinema.screens.SelectFilmScreen

@Composable
fun CinemaTicketApp(navControler:NavHostController){
    val selectFilmViewModel = SelectFilmViewModel()

    NavHost(navController = navControler, startDestination = "select_film"){
        composable(ScreenName.SelectFilmScreen.route){
            val filmSelectState = selectFilmViewModel.listFilmSelectState
            SelectFilmScreen(selectFilmViewModel,navigateToAnotherScreen = {})
        }
        composable(ScreenName.FilmInfoScreen.route){
//            get film from back stack entry
//            FilmInfo(film,navigateToAnotherScreen = {})
        }
    }
}