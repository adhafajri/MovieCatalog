package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.room.ColumnInfo

data class VideoEntity(
    @ColumnInfo(name = "videoUrl")
    var videoUrl: String?
)