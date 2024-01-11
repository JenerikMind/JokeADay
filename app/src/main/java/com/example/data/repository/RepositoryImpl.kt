package com.example.data.repository

import com.example.data.api.ApiService
import com.example.data.dtos.JokeDTO
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun getAJoke(): JokeDTO = apiService.getAJoke()
}