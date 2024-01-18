package com.example.data.repository

import com.example.data.api.ApiResponse
import com.example.data.database.JokeEntity
import com.example.data.dtos.JokeDTO
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAJoke(): JokeDTO

    suspend fun getAJokeWithState(): ApiResponse

    suspend fun getJokeWithWrapper(): ApiResponse

    suspend fun insertJokeDB(joke: JokeEntity)

    suspend fun getJokesDB(): Flow<List<JokeEntity>>

    suspend fun getJokeDB(apiId: Int): Flow<JokeEntity?>

    suspend fun checkExists(apiId: Int): Int

    suspend fun deleteJokeFromDB(apiId: Int)
}