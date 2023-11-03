package com.myapp.network.data_source

import com.myapp.network.model.PosterResponse

interface PosterRemoteDataSource {

    suspend fun getPoster(movieTitle: String): PosterResponse
}