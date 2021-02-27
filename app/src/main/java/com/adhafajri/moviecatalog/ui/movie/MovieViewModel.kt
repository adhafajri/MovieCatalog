package com.adhafajri.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    fun getPopularMovies(): LiveData<List<CatalogEntity>> = catalogRepository.getPopularMovies()
    fun getUpcomingMovies(): LiveData<List<CatalogEntity>> = catalogRepository.getUpcomingMovies()
}