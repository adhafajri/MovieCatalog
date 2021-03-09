package com.adhafajri.moviecatalog.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithJob (
    @Embedded
    var person: PersonEntity,

    @Relation(parentColumn = "personId", entityColumn = "personId")
    var jobs: List<JobEntity>,
)