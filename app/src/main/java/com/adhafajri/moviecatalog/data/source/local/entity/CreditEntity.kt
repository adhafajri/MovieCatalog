package com.adhafajri.moviecatalog.data.source.local.entity

data class CreditEntity(
    var person: PersonEntity,
    var personsJob: List<PersonJobEntity>
)