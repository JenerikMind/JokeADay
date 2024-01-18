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

    @Query ("SELECT * FROM jokeEntity WHERE api_id == :apiId")
    fun getJoke(apiId: Int): JokeEntity?

    @Query ("SELECT EXISTS(SELECT * FROM JokeEntity WHERE api_id == :apiId)")
    fun checkExists(apiId: Int): Int

    @Insert
    fun insertAll(vararg jokes: JokeEntity)

    @Query("DELETE FROM JokeEntity WHERE api_id == :apiId")
    fun deleteJoke(apiId: Int)
}