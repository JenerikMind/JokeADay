package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JokeEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "api_id") val apiId: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "setup") val setup: String,
    @ColumnInfo(name = "delivery") val delivery: String,
    @ColumnInfo(name = "flags") val flags: String,
    @ColumnInfo(name = "safe") val safe: String,
    @ColumnInfo(name = "lang") val lang: String
)