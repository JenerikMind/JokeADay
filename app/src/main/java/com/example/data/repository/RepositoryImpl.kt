package com.example.data.repository

import com.example.data.api.ApiResponse
import com.example.data.api.ApiService
import com.example.data.database.JokeDAO
import com.example.data.database.JokeEntity
import com.example.data.dtos.JokeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val jokeDAO: JokeDAO
) : Repository {
    override suspend fun getAJoke(safe: Boolean): ApiResponse {
        val response = if (safe) apiService.getASafeJoke() else apiService.getAJoke()
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

    override suspend fun getJokesDB(): Flow<List<JokeEntity>> = jokeDAO.getAll()

    override suspend fun getJokeDB(apiId: Int): Flow<JokeEntity?> = flow {
        jokeDAO.getJoke(apiId).let {
            if (it != null) emit(it) else emit(null)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun checkExists(apiId: Int): Int {
        return jokeDAO.checkExists(apiId)
    }

    override suspend fun deleteJokeFromDB(apiId: Int) {
        jokeDAO.deleteJoke(apiId)
    }
}