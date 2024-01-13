package com.example.data.repository

import com.example.data.api.ApiResponse
import com.example.data.dtos.JokeDTO

interface Repository {

    suspend fun getAJoke(): JokeDTO

    suspend fun getAJokeWithState(): ApiResponse

    suspend fun getJokeWithWrapper(): ApiResponse
}