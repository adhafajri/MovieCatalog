package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(
    tableName = "jobentities",
    primaryKeys = ["jobId", "personId"],
    foreignKeys = [ForeignKey(
        entity = PersonEntity::class,
        parentColumns = ["personId"],
        childColumns = ["personId"]
    )],
    indices = [Index(value = ["jobId"]),
        Index(value = ["personId"])]
)
data class JobEntity(
    @NonNull
    @ColumnInfo(name = "jobId")
    var jobId: String,

    @NonNull
    @ColumnInfo(name = "personId")
    var personId: String,

    @NonNull
    @ColumnInfo(name = "position")
    var position: String,
)