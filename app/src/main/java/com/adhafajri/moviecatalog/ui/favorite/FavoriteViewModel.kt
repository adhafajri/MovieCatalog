package com.adhafajri.moviecatalog.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity

class FavoriteViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    fun getFavorites(): LiveData<PagedList<CatalogEntity>> = catalogRepository.getFavoriteCatalogs()
}