package com.adhafajri.moviecatalog.data.source.remote

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse
import com.adhafajri.moviecatalog.utils.api.APIHelper

class RemoteDataSource private constructor(
    private val apiHelper: APIHelper
) {
    private val TAG = RemoteDataSource::class.java.toString()

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: APIHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        val result = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        result.value = ApiResponse.success(apiHelper.getPopularMovies())
        return result
    }

    fun getPopularTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        val result = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        result.value = ApiResponse.success(apiHelper.getPopularTvShows())
        return result
    }

    fun getUpcomingMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        val result = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        result.value = ApiResponse.success(apiHelper.getUpcomingMovies())
        return result
    }

    fun getTodayAiringTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        val result = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        result.value = ApiResponse.success(apiHelper.getTodayAiringTvShows())
        return result
    }

    fun getMovieVideos(movieId: String): MutableLiveData<ApiResponse<List<VideoResponse>>> {
        val result = MutableLiveData<ApiResponse<List<VideoResponse>>>()
        result.value = ApiResponse.success(apiHelper.getMovieVideos(movieId))
        return result
    }

    fun getTvShowVideos(tvShowId: String): MutableLiveData<ApiResponse<List<VideoResponse>>> {
        val result = MutableLiveData<ApiResponse<List<VideoResponse>>>()
        result.value = ApiResponse.success(apiHelper.getTvShowsVideos(tvShowId))
        return result
    }

    fun getMovieCredits(movieId: String): MutableLiveData<ApiResponse<List<CreditResponse>>> {
        val result = MutableLiveData<ApiResponse<List<CreditResponse>>>()
        result.value = ApiResponse.success(apiHelper.getMovieCredits(movieId))
        return result
    }

    fun getTvShowCredits(tvShowId: String): MutableLiveData<ApiResponse<List<CreditResponse>>> {
        val result = MutableLiveData<ApiResponse<List<CreditResponse>>>()
        result.value = ApiResponse.success(apiHelper.getTvShowCredits(tvShowId))
        return result
    }
}