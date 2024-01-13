package com.example.domain

import android.util.Log
import com.example.data.api.ApiResponse
import com.example.data.dtos.JokeDTO
import com.example.data.repository.Repository
import javax.inject.Inject

class GetAnyJokeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun requestSingleJoke(): JokeDTO? {
        return when (val response = repository.getJokeWithWrapper()) {
            is ApiResponse.isSuccess -> {
                Log.d("UseCase", "requestSingleJoke - Delivery: ${response.joke.delivery}")
                response.joke
            }

            is ApiResponse.isError -> {
                Log.d("UseCase", "requestSingleJoke: ${response.errorMessage}")
                null
            }
        }
    }
}