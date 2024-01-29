package com.example.data.dtos

import com.example.data.database.JokeEntity
import com.google.gson.annotations.SerializedName

data class JokeDTO(
    @SerializedName("error")
    val error: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("setup")
    val setup: String?,

    @SerializedName("delivery")
    val delivery: String?,

    @SerializedName("joke")
    val joke: String?,

    @SerializedName("flags")
    val flags: FlagsDTO? = null,

    @SerializedName("id")
    val id: Int,

    @SerializedName("safe")
    val safe: String,

    @SerializedName("lang")
    val lang: String
)

fun JokeDTO.convertToEntity(): JokeEntity{
    return JokeEntity(
        apiId = this.id,
        category = this.category,
        setup = this.setup ?: "", // if no setup, joke is single liner?
        delivery = this.delivery ?: "", // TODO: determine better way (probably just update DB)
        type = this.type,
        safe = this.safe,
        lang = this.lang,
        flags = this.flags.toString(),
        joke = this.joke
    )
}