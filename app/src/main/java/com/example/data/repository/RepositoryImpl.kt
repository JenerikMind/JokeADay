package com.example.data.repository

import com.example.data.JokeReqResponse
import com.example.data.api.ApiService
import com.example.data.database.JokeDAO
import com.example.data.database.JokeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val jokeDAO: JokeDAO
) : Repository {
    override suspend fun getAJoke(nsfw: Boolean): JokeReqResponse {
        val response = if (nsfw) apiService.getAJoke() else apiService.getASafeJoke()
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.delivery != null || it.joke != null) {
                    return JokeReqResponse.isSuccess(it)
                } else {
                    return JokeReqResponse.isError("Error with the contents of the request")
                }
            }
        }
        return JokeReqResponse.isError(
            "Error from server request - ${response.errorBody()}"
        )
    }

    override suspend fun insertJokeDB(joke: JokeEntity) {
        jokeDAO.insertAll(joke)
    }

    override suspend fun getJokesDB(): Flow<List<JokeEntity>> = jokeDAO.getAll()

    override suspend fun getJokeDB(apiId: Int): JokeReqResponse {
        val joke = jokeDAO.getJoke(apiId)

        return if (joke != null) {
            JokeReqResponse.isSuccess(joke)
        } else {
            JokeReqResponse.isError("Joke does not exist or DB error")
        }
    }

    override suspend fun checkExists(apiId: Int): Int = jokeDAO.checkExists(apiId)

    override suspend fun deleteJokeFromDB(apiId: Int) = jokeDAO.deleteJoke(apiId)

    override suspend fun searchBySetupOrDelivery(query: String): Flow<List<JokeEntity>> =
        jokeDAO.searchBySetupOrDelivery(query)
}