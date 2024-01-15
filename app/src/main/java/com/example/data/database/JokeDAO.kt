package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDAO {
    @Query ("SELECT * FROM jokeEntity")
    fun getAll(): Flow<List<JokeEntity>>

    @Insert
    fun insertAll(vararg jokes: JokeEntity)

    @Delete
    fun deleteJoke(joke: JokeEntity)
}