package com.adhafajri.moviecatalog.utils.api

import com.adhafajri.moviecatalog.BuildConfig
import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse

class APIHelper(private val apiInterface: APIInterface) {

    fun getPopularMovies(): List<MoviesResponse>? {
        val response =
            apiInterface.getPopularMovies(BuildConfig.THE_MOVIE_API_TOKEN).execute().body()
        return response?.results
    }

    fun getPopularTvShows(): List<TvShowResponse>? {
        val response =
            apiInterface.getPopularTvShows(BuildConfig.THE_MOVIE_API_TOKEN).execute().body()
        return response?.results
    }

    fun getUpcomingMovies(): List<MoviesResponse>? {
        val response =
            apiInterface.getUpcomingMovies(BuildConfig.THE_MOVIE_API_TOKEN).execute().body()
        return response?.results
    }

    fun getTodayAiringTvShows(): List<TvShowResponse>? {
        val response =
            apiInterface.getTodayAiringTvShows(BuildConfig.THE_MOVIE_API_TOKEN).execute().body()
        return response?.results
    }

    fun getMovieVideos(movieId: String): List<VideoResponse>? {
        val response =
            apiInterface.getMovieVideos(movieId, BuildConfig.THE_MOVIE_API_TOKEN).execute().body()
        return response?.results
    }

    fun getTvShowsVideos(tvShowId: String): List<VideoResponse>? {
        val response =
            apiInterface.getTvShowVideos(tvShowId, BuildConfig.THE_MOVIE_API_TOKEN).execute().body()
        return response?.results
    }

    fun getMovieCredits(movieId: String): List<CreditResponse>? {
        val response =
            apiInterface.getMovieCredits(movieId, BuildConfig.THE_MOVIE_API_TOKEN).execute().body()
        return response?.crew
    }

    fun getTvShowCredits(tvShowId: String): List<CreditResponse>? {
        val response =
            apiInterface.getTvShowCredits(tvShowId, BuildConfig.THE_MOVIE_API_TOKEN).execute()
                .body()
        return response?.crew
    }
}