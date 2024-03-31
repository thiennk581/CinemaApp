package com.example.momocinema.APIService

import com.example.momocinema.repository.FilmRespone
import com.example.momocinema.repository.RankingRespone
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private var url = "" //add later
private var retrofit =Retrofit.Builder().baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var recipeService = retrofit.create(APIService::class.java)

interface APIService{
    @GET("") //listFilm
    suspend fun getListFilm():FilmRespone
    @GET("") //ranking
    suspend fun getRanking():RankingRespone
}