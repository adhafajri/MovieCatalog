package com.adhafajri.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
)