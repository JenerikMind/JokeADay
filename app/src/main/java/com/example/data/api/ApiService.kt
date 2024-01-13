package com.example.data.api

import com.example.data.dtos.JokeDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("Any")
    suspend fun getAJoke(): JokeDTO

    @GET("Any")
    suspend fun getAJokeWithResponse(): Response<JokeDTO>
}