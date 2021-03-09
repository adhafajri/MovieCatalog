package com.adhafajri.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("key")
    var key: String,
    @SerializedName("site")
    var site: String,
    @SerializedName("type")
    var type: String,
)