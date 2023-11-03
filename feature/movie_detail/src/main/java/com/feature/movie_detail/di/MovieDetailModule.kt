package com.feature.movie_detail.di

import com.feature.movie_detail.data.MovieDetailRepositoryImpl
import com.feature.movie_detail.domain.MovieDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieListModule {

    @Binds
    abstract fun bindMovieDetailRepository(
        movieDetailRepositoryImpl: MovieDetailRepositoryImpl
    ): MovieDetailRepository
}