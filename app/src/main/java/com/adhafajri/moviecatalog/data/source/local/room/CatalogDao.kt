package com.adhafajri.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import com.adhafajri.moviecatalog.utils.Constant
import androidx.paging.DataSource
import androidx.room.*
import com.adhafajri.moviecatalog.data.source.local.entity.*

@Dao
interface CatalogDao {
    @Query("SELECT * FROM catalogentities WHERE type = :type AND listType = :listType")
    fun getMovieCatalogs(type: String, listType: String): DataSource.Factory<Int, CatalogEntity>

//    @Query("SELECT * FROM catalogentities WHERE type = ${Constant.MOVIE} AND listType = ${Constant.UPCOMING_MOVIES}")
//    fun getUpcomingMovieCatalogs(): DataSource.Factory<Int, CatalogEntity>

    @Query("SELECT * FROM catalogentities WHERE type = :type AND listType = :listType")
    fun getTvShowCatalogs(type: String, listType: String): DataSource.Factory<Int, CatalogEntity>

//    @Query("SELECT * FROM catalogentities WHERE type = ${Constant.TV_SHOW} AND listType = ${Constant.TODAY_AIRING_TV_SHOWS}")
//    fun getTodayAiringTvShowCatalogs(): DataSource.Factory<Int, CatalogEntity>

    @Query("SELECT * FROM catalogentities WHERE isFavorite = 1")
    fun getFavoriteCatalogs(): DataSource.Factory<Int, CatalogEntity>

    @Query("SELECT * FROM catalogentities WHERE catalogId = :catalogId")
    fun getCatalogById(catalogId: String): LiveData<CatalogEntity>

    @Transaction
    @Query("SELECT * FROM catalogentities WHERE catalogId = :catalogId")
    fun getCatalogWithPersonById(catalogId: String): LiveData<CatalogWithPerson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatalogs(catalogs: List<CatalogEntity>)

    @Update
    fun updateCatalog(catalog: CatalogEntity)

    @Query("UPDATE catalogentities SET videoUrl = :videoUrl WHERE catalogId = :catalogId")
    fun updateCatalogByVideo(videoUrl: String, catalogId: String)

    @Query("SELECT * FROM personentities WHERE catalogId = :catalogId")
    fun getPersonByCatalogId(catalogId: String): LiveData<List<PersonEntity>>

    @Transaction
    @Query("SELECT * FROM personentities WHERE personId = :personId")
    fun getPersonWithJobById(personId: String): LiveData<PersonWithJob>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersons(persons: List<PersonEntity>)

    @Update
    fun updatePerson(person: PersonEntity)

    @Query("SELECT * FROM jobentities WHERE personId = :personId")
    fun getJobByPersonId(personId: String): LiveData<List<JobEntity>>

    @Query("SELECT * FROM jobentities WHERE jobId = :jobId")
    fun getJobById(jobId: String): DataSource.Factory<Int, JobEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJobs(jobs: List<JobEntity>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCatalogs(catalogs: List<CatalogEntity>)
//
//    @Update
//    fun updateCatalog(catalog: CatalogEntity)
//
//    @Query("SELECT * FROM creditentities WHERE catalogId = :catalogId")

}