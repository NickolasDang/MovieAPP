package com.feature.movie_list.di

import com.feature.movie_list.data.MovieRepositoryImpl
import com.feature.movie_list.domain.MovieRepository
import com.myapp.network.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class MovieListDataModule {

    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}