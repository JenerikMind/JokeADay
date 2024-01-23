package com.example.data.repository

import com.example.data.JokeReqResponse
import com.example.data.database.JokeEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAJoke(nsfw: Boolean = true): JokeReqResponse

    suspend fun insertJokeDB(joke: JokeEntity)

    suspend fun getJokesDB(): Flow<List<JokeEntity>>

    suspend fun getJokeDB(apiId: Int): JokeReqResponse

    suspend fun checkExists(apiId: Int): Int

    suspend fun deleteJokeFromDB(apiId: Int)

    suspend fun searchBySetupOrDelivery(query: String): Flow<List<JokeEntity>>
}