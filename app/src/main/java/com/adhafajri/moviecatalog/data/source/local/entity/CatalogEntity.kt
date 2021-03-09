package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalogentities")
data class CatalogEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "catalogId")
    var catalogId: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "listType")
    val listType: String? = null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
) {
    @Embedded
    var videoEntity: VideoEntity? = null
}