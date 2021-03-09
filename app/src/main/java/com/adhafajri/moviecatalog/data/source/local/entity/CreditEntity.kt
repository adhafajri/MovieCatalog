package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation

data class CreditEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "creditId")
    var creditId: String,

    @Embedded
    var person: PersonEntity,

    @Relation(parentColumn = "personId", entityColumn = "personId")
    var personsJob: List<PersonJobEntity>? = null,
)