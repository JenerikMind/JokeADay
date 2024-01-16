package com.example.data.dtos

import com.google.gson.annotations.SerializedName

data class JokeDTO(
    @SerializedName("error")
    val error: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("setup")
    val setup: String,

    @SerializedName("delivery")
    val delivery: String,

    @SerializedName("flags")
    val flags: FlagsDTO? = null,

    @SerializedName("id")
    val id: Int,

    @SerializedName("safe")
    val safe: String,

    @SerializedName("lang")
    val lang: String
)