package com.adhafajri.moviecatalog.data.source.remote

import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse
import com.adhafajri.moviecatalog.utils.api.APIHelper

class RemoteDataSource private constructor(
    private val apiHelper: APIHelper,
) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: APIHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getPopularMovies(callback: LoadMoviesCallback) =
        callback.onAllMoviesReceived(apiHelper.getPopularMovies())

    fun getUpcomingMovies(callback: LoadMoviesCallback) =
        callback.onAllMoviesReceived(apiHelper.getUpcomingMovies())

    fun getPopularTvShows(callback: LoadTvShowsCallback) =
        callback.onAllTvShowsReceived(apiHelper.getPopularTvShows())

    fun getTodayAiringTvShows(callback: LoadTvShowsCallback) =
        callback.onAllTvShowsReceived(apiHelper.getTodayAiringTvShows())

    fun getMovieVideos(movieId: String, callback: LoadVideosCallback) =
        callback.onAllVideosReceived(apiHelper.getMovieVideos(movieId))

    fun getTvShowVideos(tvShowId: String, callback: LoadVideosCallback) =
        callback.onAllVideosReceived(apiHelper.getTvShowsVideos(tvShowId))

    fun getMovieCredits(movieId: String, callback: LoadCreditsCallback) =
        callback.onAllCreditsReceived(apiHelper.getMovieCredits(movieId))

    fun getTvShowCredits(tvShowId: String, callback: LoadCreditsCallback) =
        callback.onAllCreditsReceived(apiHelper.getTvShowCredits(tvShowId))

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>?)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>?)
    }

    interface LoadVideosCallback {
        fun onAllVideosReceived(videosResponse: List<VideoResponse>?)
    }

    interface LoadCreditsCallback {
        fun onAllCreditsReceived(creditResponse: List<CreditResponse>?)
    }
}