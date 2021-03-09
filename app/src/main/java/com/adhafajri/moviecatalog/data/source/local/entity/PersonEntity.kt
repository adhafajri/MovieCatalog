package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(
    tableName = "personentities",
    primaryKeys = ["personId", "catalogId"],
    foreignKeys = [ForeignKey(
        entity = CatalogEntity::class,
        parentColumns = ["catalogId"],
        childColumns = ["catalogId"]
    )],
    indices = [Index(value = ["personId"]),
        Index(value = ["catalogId"])]
)
data class PersonEntity(
    @NonNull
    @ColumnInfo(name = "personId")
    var personId: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "catalogId")
    var catalogId: String,

    @ColumnInfo(name = "name")
    var name: String,
)
