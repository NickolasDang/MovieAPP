package com.myapp.network.api

import com.myapp.network.NetworkConstants
import com.myapp.network.model.ITunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApiService {

    @GET(NetworkConstants.SEARCH)
    suspend fun getMovieList(
        @Query("term") input: String,
        @Query("country") country: String = "au",
        @Query("media")  media: String = "movie"
    ) : ITunesResponse
}
