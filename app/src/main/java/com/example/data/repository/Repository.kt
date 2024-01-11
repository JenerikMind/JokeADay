package com.example.data.repository

import com.example.data.dtos.JokeDTO

interface Repository {

    suspend fun getAJoke(): JokeDTO
}