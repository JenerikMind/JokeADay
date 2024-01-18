package com.example.data.api

import com.example.data.dtos.JokeDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("Any")
    suspend fun getAJoke(): Response<JokeDTO>

    @GET("Any?safe-mode")
    suspend fun getASafeJoke(): Response<JokeDTO>
}