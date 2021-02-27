package com.adhafajri.moviecatalog.data

import androidx.lifecycle.LiveData
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CreditEntity
import com.adhafajri.moviecatalog.data.source.local.entity.VideoEntity

interface CatalogDataSource {
    fun getPopularMovies(): LiveData<List<CatalogEntity>>
    fun getUpcomingMovies(): LiveData<List<CatalogEntity>>
    fun getMovieCredits(movieId: String): LiveData<List<CreditEntity>>
    fun getMovieVideo(movieId: String): LiveData<VideoEntity>

    fun getPopularTvShows(): LiveData<List<CatalogEntity>>
    fun getTodayAiringTvShows(): LiveData<List<CatalogEntity>>
    fun getTvShowCredits(tvShowId: String): LiveData<List<CreditEntity>>
    fun getTvShowVideo(tvShowId: String): LiveData<VideoEntity>
}