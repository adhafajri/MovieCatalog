package com.adhafajri.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogWithPerson
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity

@Dao
interface CatalogDao {
    @Query("SELECT * FROM catalogentities WHERE type = :type AND listType = :listType")
    fun getMovieCatalogs(type: String, listType: String): DataSource.Factory<Int, CatalogEntity>

    @Query("SELECT * FROM catalogentities WHERE type = :type AND listType = :listType")
    fun getTvShowCatalogs(type: String, listType: String): DataSource.Factory<Int, CatalogEntity>

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersons(persons: List<PersonEntity>)
}