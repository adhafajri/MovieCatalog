package com.adhafajri.moviecatalog.utils.api

import com.adhafajri.moviecatalog.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIInterface {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<MoviesResponse>>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<TvShowResponse>>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<MoviesResponse>>

    @GET("tv/airing_today")
    fun getTodayAiringTvShows(
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<TvShowResponse>>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<VideoResponse>>

    @GET("tv/{tv_id}/videos")
    fun getTvShowVideos(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<VideoResponse>>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<CreditResponse>>

    @GET("tv/{tv_id}/credits")
    fun getTvShowCredits(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
    ): Call<BaseResponse<CreditResponse>>
}