package com.adhafajri.moviecatalog.data

data class CatalogEntity(
        var catalogId: String,
        var title: String,
        var year: String,
        var posterPath: String,
        var trailerPath: String,
        var synopsis: String,
        var directors: String,
        var writers: String,
        var stars: String
)