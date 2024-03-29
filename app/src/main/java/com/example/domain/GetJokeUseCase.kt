package com.example.domain

import com.example.data.JokeReqResponse
import com.example.data.database.JokeEntity
import com.example.data.database.convertIntoDTO
import com.example.data.dtos.JokeDTO
import com.example.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJokeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun requestSingleJoke(isSafe: Boolean = true): JokeDTO? {
        return when (val response = repository.getAJoke(isSafe)) {
            is JokeReqResponse.isSuccess<*> -> {
                response.joke as JokeDTO
            }

            is JokeReqResponse.isError -> {
                null
            }
        }
    }

    suspend fun requestJokeDb(apiId: Int): JokeDTO? {
        return when (val response = repository.getJokeDB(apiId)) {
            is JokeReqResponse.isSuccess<*> -> {
                val jokeEnt = response.joke as JokeEntity
                jokeEnt.convertIntoDTO()
            }

            is JokeReqResponse.isError -> {
                null
            }
        }
    }

    suspend fun requestAllJokesDb(): Flow<List<JokeEntity>> {
        return repository.getJokesDB()
    }

    suspend fun checkIfJokeExists(apiID: Int): Boolean {
        return repository.checkExists(apiID) == 1
    }

    suspend fun searchBySetupOrDelivery(query: String): Flow<List<JokeEntity>> {
        val amendedQuery = "%${query}%"
        return repository.searchBySetupOrDelivery(amendedQuery)
    }
}