package com.adhafajri.moviecatalog.data

data class PersonEntity(
    var personId: String,
    var catalogId: String,
    var name: String,
    var titles: List<TitleEntity>? = null
)
