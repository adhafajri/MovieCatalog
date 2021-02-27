package com.adhafajri.moviecatalog.data

import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource

class TestCatalogRepository(private val remoteDataSource: RemoteDataSource) : CatalogDataSource {
}