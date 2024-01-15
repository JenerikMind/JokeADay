package com.example.data.repository

import com.example.data.api.ApiResponse
import com.example.data.api.ApiService
import com.example.data.database.JokeDAO
import com.example.data.database.JokeEntity
import com.example.data.dtos.JokeDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val jokeDAO: JokeDAO
) : Repository {
    override suspend fun getAJoke(): JokeDTO = apiService.getAJoke()

    override suspend fun getAJokeWithState(): ApiResponse {
        try {
            val response = apiService.getAJoke()
            if (response.error == "false") return ApiResponse.isSuccess(response)
            return ApiResponse.isError("Error from the api")
        } catch (e: Exception) {
            return ApiResponse.isError("Error from the repository: ${e.message}")
        }
    }

    override suspend fun getJokeWithWrapper(): ApiResponse {
        val response = apiService.getAJokeWithResponse()
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.delivery != null) {
                    return ApiResponse.isSuccess(it)
                } else {
                    return ApiResponse.isError("Null delivery...")
                }
            }
        }
        return ApiResponse.isError(
            "Some sort of error has occured... ${response.errorBody()}"
        )
    }

    override suspend fun insertJokeDB(joke: JokeEntity) {
        jokeDAO.insertAll(joke)
    }

    override suspend fun getJokes(): Flow<List<JokeEntity>> = jokeDAO.getAll()

}