package com.myapp.network

import javax.inject.Inject

class MovieListRemoteDataSource @Inject constructor(
    private val iTunesApiService: ITunesApiService
) {
    suspend fun getMovieList(input: String) =
        iTunesApiService.getMovieList(input, media = "movie")
}