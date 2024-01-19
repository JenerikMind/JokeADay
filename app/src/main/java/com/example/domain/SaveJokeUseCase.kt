package com.example.domain

import com.example.data.database.JokeEntity
import com.example.data.dtos.JokeDTO
import com.example.data.dtos.convertToEntity
import com.example.data.repository.Repository
import javax.inject.Inject

class SaveJokeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun saveJoke(joke: JokeDTO){
        val jokeEntity = joke.convertToEntity()
        repository.insertJokeDB(jokeEntity)
    }
}