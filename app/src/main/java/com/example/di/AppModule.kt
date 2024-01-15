package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.api.ApiService
import com.example.data.database.Database
import com.example.data.database.JokeDAO
import com.example.data.repository.Repository
import com.example.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/joke/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, jokeDAO: JokeDAO): Repository {
        return RepositoryImpl(apiService, jokeDAO)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java, "jokes"
        ).build()
    }

    @Provides
    @Singleton
    fun provideJokeDao(db: Database): JokeDAO = db.jokeDao()
}