package com.adhafajri.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

data class BaseResponse<T>(
    @SerializedName("results")
    var results: @RawValue List<T>,
    @SerializedName("crew")
    var crew: @RawValue List<T>,
)