package com.adhafajri.moviecatalog.di

import android.content.Context
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.LocalDataSource
import com.adhafajri.moviecatalog.data.source.local.room.CatalogDatabase
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.utils.AppExecutors
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIHelper
import com.adhafajri.moviecatalog.utils.api.APIInterface

object Injection {
    fun provideRepository(context: Context): CatalogRepository {

        val database = CatalogDatabase.getInstance(context)

        val apiHelper = APIHelper.getInstance(
            APIClient().getClient().create(
                APIInterface::class.java
            )
        )

        val remoteDataSource = RemoteDataSource.getInstance(apiHelper)
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()

        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
