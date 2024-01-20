package com.example.domain

import com.example.data.dtos.JokeDTO
import com.example.data.dtos.convertToEntity
import com.example.data.repository.Repository
import javax.inject.Inject

class SaveJokeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun saveJoke(joke: JokeDTO): Boolean {
        val jokeEntity = joke.convertToEntity()

        return if (repository.checkExists(joke.id) == 1){
            repository.insertJokeDB(jokeEntity)
            true
        }else {
            false
        }
    }
}