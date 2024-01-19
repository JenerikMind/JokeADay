package com.example.data

sealed class JokeReqResponse {
    data class isSuccess<T>(val joke: T) : JokeReqResponse()
    data class isError(val errorMessage: String) : JokeReqResponse()

}