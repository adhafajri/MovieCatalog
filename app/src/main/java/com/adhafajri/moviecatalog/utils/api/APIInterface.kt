package com.adhafajri.moviecatalog.utils.api

import com.adhafajri.moviecatalog.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIInterface {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<MoviesResponse>>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<MoviesResponse>>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<CreditResponse>>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<VideoResponse>>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<TvShowResponse>>

    @GET("tv/airing_today")
    fun getTodayAiringTvShows(
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<TvShowResponse>>

    @GET("tv/{tv_id}")
    fun getTvShowDetails(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("tv/{tv_id}/credits")
    fun getTvShowCredits(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<CreditResponse>>

    @GET("tv/{tv_id}/videos")
    fun getTvShowVideos(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String
    ): Call<BaseResponse<VideoResponse>>
}