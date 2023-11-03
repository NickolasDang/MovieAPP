package com.myapp.network.di

import com.myapp.network.data_source.MovieListRemoteDataSource
import com.myapp.network.data_source.MovieListRemoteDataSourceImpl
import com.myapp.network.data_source.PosterRemoteDataSource
import com.myapp.network.data_source.PosterRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBinderModule {

    @Binds
    abstract fun bindMovieListRemoteDataSource(
        movieListRemoteDataSourceImpl: MovieListRemoteDataSourceImpl
    ): MovieListRemoteDataSource

    @Binds
    abstract fun bindPosterRemoteDataSource(
        posterRemoteDataSourceImpl: PosterRemoteDataSourceImpl
    ): PosterRemoteDataSource
}