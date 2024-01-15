package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokeEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun jokeDao(): JokeDAO
}