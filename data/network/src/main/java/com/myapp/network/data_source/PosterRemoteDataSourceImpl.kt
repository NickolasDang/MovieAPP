package com.myapp.network.data_source

import com.myapp.network.NetworkConstants
import com.myapp.network.api.OmdbApiService
import javax.inject.Inject

class PosterRemoteDataSourceImpl @Inject constructor(
    private val omdbApiService: OmdbApiService
) : PosterRemoteDataSource {

    override suspend fun getPoster(movieTitle: String) =
        omdbApiService.getPoster(
            apiKey = NetworkConstants.OMBD_API_KEY,
            movieTitle = movieTitle
        )
}