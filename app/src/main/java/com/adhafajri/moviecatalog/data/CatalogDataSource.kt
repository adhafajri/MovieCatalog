package com.adhafajri.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogWithPerson
import com.adhafajri.moviecatalog.vo.Resource

interface CatalogDataSource {
    fun getPopularMovies(): LiveData<Resource<PagedList<CatalogEntity>>>
    fun getUpcomingMovies(): LiveData<Resource<PagedList<CatalogEntity>>>
    fun getPopularTvShows(): LiveData<Resource<PagedList<CatalogEntity>>>
    fun getTodayAiringTvShows(): LiveData<Resource<PagedList<CatalogEntity>>>
    fun getCatalogWithPersons(
        catalogId: String,
        type: String
    ): LiveData<Resource<CatalogWithPerson>>

    fun getVideo(catalogId: String, type: String): LiveData<Resource<CatalogEntity>>
    fun getFavoriteCatalogs(): LiveData<PagedList<CatalogEntity>>
    fun setCatalogFavorite(catalog: CatalogEntity, state: Boolean)
}