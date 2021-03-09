package com.adhafajri.moviecatalog.utils.api

import android.content.Context
import android.util.Log
import com.adhafajri.moviecatalog.BuildConfig
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse

class APIHelper(private val apiInterface: APIInterface) {
    private val TAG = APIHelper::class.java.toString()

    companion object {
        @Volatile
        private var instance: APIHelper? = null

        fun getInstance(apiInterface: APIInterface): APIHelper =
            instance ?: synchronized(this) {
                instance ?: APIHelper(apiInterface)
            }
    }

    fun getPopularMovies(): List<MoviesResponse> {
        val response =
            apiInterface.getPopularMovies(BuildConfig.THE_MOVIE_API_TOKEN).execute()

        val result = ArrayList<MoviesResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }

    fun getPopularTvShows(): List<TvShowResponse> {
        val response =
            apiInterface.getPopularTvShows(BuildConfig.THE_MOVIE_API_TOKEN)
                .execute()

        val result = ArrayList<TvShowResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }

    fun getUpcomingMovies(): List<MoviesResponse> {
        val response =
            apiInterface.getUpcomingMovies(BuildConfig.THE_MOVIE_API_TOKEN).execute()

        val result = ArrayList<MoviesResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }

    fun getTodayAiringTvShows(): List<TvShowResponse> {
        val response =
            apiInterface.getTodayAiringTvShows(BuildConfig.THE_MOVIE_API_TOKEN)
                .execute()

        val result = ArrayList<TvShowResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }

    fun getMovieVideos(movieId: String): List<VideoResponse> {
        val response =
            apiInterface.getMovieVideos(movieId, BuildConfig.THE_MOVIE_API_TOKEN)
                .execute()

        val result = ArrayList<VideoResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }

    fun getTvShowsVideos(tvShowId: String): List<VideoResponse> {
        val response =
            apiInterface.getTvShowVideos(tvShowId, BuildConfig.THE_MOVIE_API_TOKEN)
                .execute()

        val result = ArrayList<VideoResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }

    fun getMovieCredits(movieId: String): List<CreditResponse> {
        val response =
            apiInterface.getMovieCredits(movieId, BuildConfig.THE_MOVIE_API_TOKEN)
                .execute()

        val result = ArrayList<CreditResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }

    fun getTvShowCredits(tvShowId: String): List<CreditResponse> {
        val response =
            apiInterface.getTvShowCredits(tvShowId, BuildConfig.THE_MOVIE_API_TOKEN)
                .execute()

        val result = ArrayList<CreditResponse>()
        if (response.isSuccessful) {
            response.body()?.results?.let { result.addAll(it) }
        } else {
            response.errorBody()?.toString()?.let {
                Log.d(TAG, "onResponse: $it")
            }
        }

        return result
    }
}