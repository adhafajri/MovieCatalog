package com.adhafajri.moviecatalog.utils.api

import com.adhafajri.moviecatalog.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.THE_MOVIE_DB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}