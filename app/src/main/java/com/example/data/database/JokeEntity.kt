package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.dtos.JokeDTO

@Entity
data class JokeEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "api_id") val apiId: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "setup") val setup: String,
    @ColumnInfo(name = "delivery") val delivery: String,
    @ColumnInfo(name = "flags") val flags: String,
    @ColumnInfo(name = "safe") val safe: String,
    @ColumnInfo(name = "lang") val lang: String
)

fun JokeEntity.convertIntoDTO(): JokeDTO {
    return JokeDTO(
        error = "false",
        category = this.category,
        setup = this.setup,
        delivery = this.delivery,
        type = this.type,
        id = this.apiId,
        lang = this.lang,
        safe = this.safe,
        joke = null
    )
}
