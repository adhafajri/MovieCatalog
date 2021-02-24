package com.adhafajri.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.source.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CreditEntity
import com.adhafajri.moviecatalog.data.source.local.entity.VideoEntity
import com.adhafajri.moviecatalog.utils.Constant

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    private lateinit var catalogId: String
    private lateinit var type: String

    fun setSelectedCatalog(catalogId: String, type: String) {
        this.catalogId = catalogId
        this.type = type
    }

    fun getDetails(): LiveData<CatalogEntity>? {
        return when (type) {
            Constant.MOVIE -> catalogRepository.getMovieDetails(catalogId)
            Constant.TV_SHOW -> catalogRepository.getTvShowDetails(catalogId)
            else -> null
        }
    }

    fun getCredits(): LiveData<List<CreditEntity>>? {
        return when (type) {
            Constant.MOVIE -> catalogRepository.getMovieCredits(catalogId)
            Constant.TV_SHOW -> catalogRepository.getTvShowCredits(catalogId)
            else -> null
        }
    }

    fun getVideo(): LiveData<VideoEntity>? {
        return when (type) {
            Constant.MOVIE -> catalogRepository.getMovieVideo(catalogId)
            Constant.TV_SHOW -> catalogRepository.getTvShowVideo(catalogId)
            else -> null
        }
    }
}