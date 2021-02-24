package com.adhafajri.moviecatalog.data.source.remote

import android.os.Handler
import android.os.Looper
import com.adhafajri.moviecatalog.BuildConfig
import com.adhafajri.moviecatalog.data.source.remote.response.*
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.EspressoIdlingResource
import com.adhafajri.moviecatalog.utils.api.APIInterface
import retrofit2.Callback

class RemoteDataSource private constructor(
    private val apiInterface: APIInterface
) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: APIInterface): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }

    }

    fun getPopularMovies(callback: Callback<BaseResponse<MoviesResponse>>) {
        handlerPostDelayed(
            apiInterface.getPopularMovies(BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getUpcomingMovies(callback: Callback<BaseResponse<MoviesResponse>>) {
        handlerPostDelayed(
            apiInterface.getUpcomingMovies(BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getMovieDetails(id: String, callback: Callback<MoviesResponse>) {
        handlerPostDelayed(
            apiInterface.getMovieDetails(id, BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getMovieCredits(id: String, callback: Callback<BaseResponse<CreditResponse>>) {
        handlerPostDelayed(
            apiInterface.getMovieCredits(id, BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getMovieVideos(id: String, callback: Callback<BaseResponse<VideoResponse>>) {
        handlerPostDelayed(
            apiInterface.getMovieVideos(id, BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getPopularTvShows(callback: Callback<BaseResponse<TvShowResponse>>) {
        handlerPostDelayed(
            apiInterface.getPopularTvShows(BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getTodayAiringTvShows(callback: Callback<BaseResponse<TvShowResponse>>) {
        handlerPostDelayed(
            apiInterface.getTodayAiringTvShows(BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getTvShowDetails(id: String, callback: Callback<TvShowResponse>) {
        handlerPostDelayed(
            apiInterface.getTvShowDetails(id, BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getTvShowCredits(id: String, callback: Callback<BaseResponse<CreditResponse>>) {
        handlerPostDelayed(
            apiInterface.getTvShowCredits(id, BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    fun getTvShowVideos(id: String, callback: Callback<BaseResponse<VideoResponse>>) {
        handlerPostDelayed(
            apiInterface.getTvShowVideos(id, BuildConfig.THE_MOVIE_API_TOKEN).enqueue(callback)
        )
    }

    private fun handlerPostDelayed(enqueue: Unit) {
        EspressoIdlingResource.increment()

        Handler(Looper.getMainLooper()).postDelayed({
            enqueue
            EspressoIdlingResource.decrement()
        }, Constant.SERVICE_LATENCY_IN_MILLIS)
    }
}