package com.myapp.network.model

import com.squareup.moshi.Json

data class ITunesResponse(
    val resultCount: Int,
    @Json(name = "results")
    val iTunesItemList: List<ITunesItemResponse>
)
