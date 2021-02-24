package com.adhafajri.moviecatalog.di

import com.adhafajri.moviecatalog.data.source.CatalogRepository
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIInterface

object Injection {
    fun provideRepository(): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance(
            APIClient().getClient()
                .create(APIInterface::class.java)
        )
        return CatalogRepository.getInstance(remoteDataSource)
    }
}
