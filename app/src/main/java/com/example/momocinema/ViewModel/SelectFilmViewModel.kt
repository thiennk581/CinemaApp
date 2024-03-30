package com.example.momocinema.ViewModel

import android.icu.util.Calendar
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.recipeService
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.RANKING
import kotlinx.coroutines.launch

class SelectFilmViewModel:ViewModel() {
    init {
        fetchListFilm()
    }
    private var _listFilmSelectState = mutableStateOf(FilmSelectState())
    var listFilmSelectState = _listFilmSelectState
    var calendar = Calendar.getInstance()


    private fun filterListPerforming(list:List<FILM>): List<FILM> {

        return list
    }
    private fun filterListHaventPerformed(list:List<FILM>): List<FILM> {

        return list
    }
    private fun filterListOutstanding(list:List<FILM>): List<FILM> {
        // handle later
        return list
    }

    fun fetchListFilm(){
        viewModelScope.launch {
            try {
                val fetchFilmRespone = recipeService.getListFilm()
                val fetchRankingRespone = recipeService.getRanking()
                _listFilmSelectState.value = _listFilmSelectState.value.copy(
                    loading = false,
                    error  =false,
                    listFilm = fetchFilmRespone.FilmList,
                    listRanking = fetchRankingRespone.RankingList,
                    listFilmOutstanding = filterListOutstanding(fetchFilmRespone.FilmList),
                    listFilmPerforming = filterListPerforming(fetchFilmRespone.FilmList),
                    listFilmHaventPerformed = filterListHaventPerformed(fetchFilmRespone.FilmList),
                )
            }catch (e:Exception){
                _listFilmSelectState.value = _listFilmSelectState.value.copy(
                    loading = false,
                    error = true
                )
            }
        }
    }
    data class FilmSelectState(
        val loading:Boolean = false,
        val error:Boolean = false,
        val listFilm:List<FILM> = listOf(),
        val listRanking:List<RANKING> = listOf(),
        val listFilmOutstanding:List<FILM> = listOf(),
        val listFilmPerforming:List<FILM> = listOf(),
        val listFilmHaventPerformed:List<FILM> = listOf(),
    )
}

