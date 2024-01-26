package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [JokeEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDAO
}