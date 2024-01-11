package com.example.data.api

import com.example.data.dtos.JokeDTO
import retrofit2.http.GET

interface ApiService {
    @GET("Any")
    suspend fun getAJoke(): JokeDTO
}