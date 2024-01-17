package com.example.domain

import com.example.data.repository.Repository
import javax.inject.Inject

class CheckIfExistsInDBUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun check(apiID: Int): Int {
        return repository.checkExists(apiID)
    }
}