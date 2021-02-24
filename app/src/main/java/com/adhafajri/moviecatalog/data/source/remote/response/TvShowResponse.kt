package com.adhafajri.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
)