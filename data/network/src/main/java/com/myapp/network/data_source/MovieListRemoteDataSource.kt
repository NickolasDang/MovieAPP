package com.myapp.network.data_source

import com.myapp.network.model.ITunesResponse

interface MovieListRemoteDataSource {
    suspend fun getMovieList(input: String): ITunesResponse
}