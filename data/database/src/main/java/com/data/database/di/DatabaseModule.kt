package com.data.database.di

import android.app.Application
import androidx.room.Room
import com.data.database.MovieDatabase
import com.data.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MovieDatabase =
        Room.databaseBuilder(application, MovieDatabase::class.java, "movie_database")
            .build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.dao
}