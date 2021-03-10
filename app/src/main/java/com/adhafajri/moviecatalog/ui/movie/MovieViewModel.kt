package com.adhafajri.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.vo.Resource

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    fun getPopularMovies(): LiveData<Resource<PagedList<CatalogEntity>>> =
        catalogRepository.getPopularMovies()

    fun getUpcomingMovies(): LiveData<Resource<PagedList<CatalogEntity>>> =
        catalogRepository.getUpcomingMovies()
}