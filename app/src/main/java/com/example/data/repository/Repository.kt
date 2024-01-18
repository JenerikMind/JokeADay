package com.example.data.repository

import com.example.data.api.ApiResponse
import com.example.data.database.JokeEntity
import kotlinx.coroutines.flow.Flow

interface Repository {


    suspend fun getAJoke(safe: Boolean = true): ApiResponse

    suspend fun insertJokeDB(joke: JokeEntity)


    suspend fun getJokesDB(): Flow<List<JokeEntity>>

    suspend fun getJokeDB(apiId: Int): Flow<JokeEntity?>

    suspend fun checkExists(apiId: Int): Int

    suspend fun deleteJokeFromDB(apiId: Int)
}