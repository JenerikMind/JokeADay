package com.example.domain

import com.example.data.repository.Repository
import javax.inject.Inject

class DeleteJokeUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun deleteJoke(apiId: Int){
        repository.deleteJokeFromDB(apiId)
    }
}