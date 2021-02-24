package com.adhafajri.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
)
