package com.adhafajri.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity

class TvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    fun getPopularTvShows(): LiveData<List<CatalogEntity>> = catalogRepository.getPopularTvShows()
    fun getTodayAiringTvShows(): LiveData<List<CatalogEntity>> =
        catalogRepository.getTodayAiringTvShows()
}