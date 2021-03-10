package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CatalogWithPerson(
    @Embedded
    var catalog: CatalogEntity,

    @Relation(parentColumn = "catalogId", entityColumn = "catalogId")
    var persons: List<PersonEntity>
)