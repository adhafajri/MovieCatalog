package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.room.ColumnInfo

data class VideoEntity(
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "videoId")
//    var videoId: String,
//
//    @ColumnInfo(name = "key")
//    var key: String,
//
//    @ColumnInfo(name = "site")
//    var site: String,

    @ColumnInfo(name = "videoUrl")
    var videoUrl: String?
)