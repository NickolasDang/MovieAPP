package com.myapp.network.data_source

import com.myapp.network.api.ITunesApiService
import javax.inject.Inject

class MovieListRemoteDataSource @Inject constructor(
    private val iTunesApiService: ITunesApiService
) {
    suspend fun getMovieList(input: String) =
        iTunesApiService.getMovieList(input, media = "movie")
}