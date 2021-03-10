package com.adhafajri.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.vo.Resource

class TvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    fun getPopularTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> =
        catalogRepository.getPopularTvShows()

    fun getTodayAiringTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> =
        catalogRepository.getTodayAiringTvShows()
}