package com.adhafajri.moviecatalog.data

data class CatalogEntity(
        var catalogId: String,
        var type: String,
        var title: String,
        var year: String,
        var posterPath: String,
        var trailerPath: String,
        var overview: String
)