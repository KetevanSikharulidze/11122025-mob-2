package com.example.a11122025_mob_2

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("jokes/random")
    suspend fun getJokes(): Response<DataModel>
}