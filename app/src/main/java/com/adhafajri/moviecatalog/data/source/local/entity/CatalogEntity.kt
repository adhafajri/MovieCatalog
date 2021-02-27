package com.adhafajri.moviecatalog.data.source.local.entity

data class CatalogEntity(
        var catalogId: String,
        var type: String,
        var title: String,
        var posterPath: String,
        var overview: String,
)