package com.adhafajri.moviecatalog.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.EspressoIdlingResource
import com.adhafajri.moviecatalog.utils.api.APIHelper

class RemoteDataSource private constructor(
    private val apiHelper: APIHelper
) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: APIHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getPopularMovies())
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getPopularTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getPopularTvShows())
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getUpcomingMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getUpcomingMovies())
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getTodayAiringTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getTodayAiringTvShows())
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getMovieVideos(movieId: String): MutableLiveData<ApiResponse<List<VideoResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<VideoResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getMovieVideos(movieId))
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getTvShowVideos(tvShowId: String): MutableLiveData<ApiResponse<List<VideoResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<VideoResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getTvShowsVideos(tvShowId))
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getMovieCredits(movieId: String): MutableLiveData<ApiResponse<List<CreditResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<CreditResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getMovieCredits(movieId))
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getTvShowCredits(tvShowId: String): MutableLiveData<ApiResponse<List<CreditResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<CreditResponse>>>()
        Handler(Looper.getMainLooper()).postDelayed({
            result.value = ApiResponse.success(apiHelper.getTvShowCredits(tvShowId))
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
        return result
    }
}