package com.myapp.network.api

import com.myapp.network.model.PosterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {

    @GET("/")
    suspend fun getPoster(
        @Query("apikey") apiKey: String,
        @Query("t") movieTitle: String
    ) : PosterResponse
}