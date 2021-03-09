package com.adhafajri.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.source.local.entity.*
import com.dicoding.academies.vo.Resource

interface CatalogDataSource {
    fun getPopularMovies(): LiveData<Resource<PagedList<CatalogEntity>>>
    fun getUpcomingMovies(): LiveData<Resource<PagedList<CatalogEntity>>>
//    fun getMovieCredits(movieId: String): LiveData<Resource<PagedList<CreditEntity>>>
//    fun getMovieVideo(movieId: String): LiveData<Resource<VideoEntity>>

    fun getPopularTvShows(): LiveData<Resource<PagedList<CatalogEntity>>>
    fun getTodayAiringTvShows(): LiveData<Resource<PagedList<CatalogEntity>>>
//    fun getTvShowCredits(tvShowId: String): LiveData<Resource<PagedList<CreditEntity>>>
//    fun getTvShowVideo(tvShowId: String): LiveData<Resource<VideoEntity>>

    fun getCatalogWithPersons(catalogId: String, type: String): LiveData<Resource<CatalogWithPerson>>

    fun getAllPersonByCatalogId(catalogId: String, type: String): LiveData<Resource<List<PersonEntity>>>

    fun getPersonWithJobs(catalogId: String, personId: String, type: String): LiveData<Resource<PersonWithJob>>

    fun getVideo(catalogId: String, type: String): LiveData<Resource<CatalogEntity>>

    fun getFavoriteCatalogs(): LiveData<PagedList<CatalogEntity>>

    fun setCatalogFavorite(catalog: CatalogEntity, state: Boolean)
}