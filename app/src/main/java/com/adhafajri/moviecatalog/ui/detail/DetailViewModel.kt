package com.adhafajri.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogWithPerson
import com.adhafajri.moviecatalog.vo.Resource

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    private var catalogId = MutableLiveData<String>()
    private lateinit var type: String

    fun setSelectedCatalog(catalogId: String, type: String) {
        this.catalogId.value = catalogId
        this.type = type
    }

    var catalogPerson: LiveData<Resource<CatalogWithPerson>> =
        Transformations.switchMap(catalogId) {
            catalogRepository.getCatalogWithPersons(it, type)
        }

    var videoUrl: LiveData<Resource<CatalogEntity>> =
        Transformations.switchMap(catalogId) {
            catalogRepository.getVideo(it, type)
        }

    fun setFavorite() {
        val catalogPersonResource = catalogPerson.value
        if (catalogPersonResource != null) {
            val catalogWithPerson = catalogPersonResource.data

            if (catalogWithPerson != null) {
                val catalog = catalogWithPerson.catalog
                val newState = !catalog.isFavorite
                catalogRepository.setCatalogFavorite(catalog, newState)
            }
        }
    }
}