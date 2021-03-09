package com.adhafajri.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.adhafajri.moviecatalog.data.source.local.entity.*
import com.adhafajri.moviecatalog.data.source.local.room.CatalogDao
import com.adhafajri.moviecatalog.utils.Constant

class LocalDataSource private constructor(private val catalogDao: CatalogDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(catalogDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getPopularMovieCatalogs(): DataSource.Factory<Int, CatalogEntity> =
        catalogDao.getMovieCatalogs(Constant.MOVIE, Constant.POPULAR_MOVIES)

    fun getUpcomingMovieCatalogs(): DataSource.Factory<Int, CatalogEntity> =
        catalogDao.getMovieCatalogs(Constant.MOVIE, Constant.UPCOMING_MOVIES)

    fun getPopularTvShowCatalogs(): DataSource.Factory<Int, CatalogEntity> =
        catalogDao.getTvShowCatalogs(Constant.TV_SHOW, Constant.POPULAR_TV_SHOWS)

    fun getTodayAiringTvShowCatalogs(): DataSource.Factory<Int, CatalogEntity> =
        catalogDao.getTvShowCatalogs(Constant.TV_SHOW, Constant.TODAY_AIRING_TV_SHOWS)

    fun getFavoriteCatalogs(): DataSource.Factory<Int, CatalogEntity> =
        catalogDao.getFavoriteCatalogs()

    fun getCatalogWithPersons(catalogId: String): LiveData<CatalogWithPerson> =
        catalogDao.getCatalogWithPersonById(catalogId)

    fun getAllPersonsByCatalogId(catalogId: String): LiveData<List<PersonEntity>> =
        catalogDao.getPersonByCatalogId(catalogId)

    fun getPersonWithJobs(personId: String): LiveData<PersonWithJob> =
        catalogDao.getPersonWithJobById(personId)

    fun getAllJobsByPersonId(personId: String): LiveData<List<JobEntity>> =
        catalogDao.getJobByPersonId(personId)

    fun insertCatalogs(catalogs: List<CatalogEntity>) {
        catalogDao.insertCatalogs(catalogs)
    }

    fun insertPersons(persons: List<PersonEntity>) {
        catalogDao.insertPersons(persons)
    }

    fun insertJobs(jobs: List<JobEntity>) {
        catalogDao.insertJobs(jobs)
    }

    fun setCatalogFavorite(catalog: CatalogEntity, newState: Boolean) {
        catalog.isFavorite = newState
        catalogDao.updateCatalog(catalog)
    }

    fun getCatalogWithVideo(catalogId: String): LiveData<CatalogEntity> =
        catalogDao.getCatalogById(catalogId)

    fun updateVideo(videoUrl: String, catalogId: String) {
        catalogDao.updateCatalogByVideo(videoUrl, catalogId)
    }
}