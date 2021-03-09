package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PersonJobEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "personId")
    var personId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "job")
    var job: String,
)
