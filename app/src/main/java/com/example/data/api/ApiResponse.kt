package com.example.data.api

import com.example.data.dtos.JokeDTO

sealed class ApiResponse {
    data class isSuccess(val joke: JokeDTO) : ApiResponse()
    data class isError(val errorMessage: String) : ApiResponse()

}