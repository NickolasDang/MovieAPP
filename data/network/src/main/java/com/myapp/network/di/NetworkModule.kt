package com.myapp.network.di

import com.myapp.network.api.ITunesApiService
import com.myapp.network.NetworkConstants
import com.myapp.network.api.OmdbApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    @Movie
    fun provideRetrofitMovie(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConstants.ITUNES_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    @Poster
    fun provideRetrofitPoster(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConstants.OMBD_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideITunesApiService(@Movie retrofit: Retrofit): ITunesApiService =
        retrofit.create(ITunesApiService::class.java)

    @Provides
    @Singleton
    fun provideOmdbApiService(@Poster retrofit: Retrofit): OmdbApiService =
        retrofit.create(OmdbApiService::class.java)
}