package com.adhafajri.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhafajri.moviecatalog.data.source.local.entity.*
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.data.source.remote.response.*
import com.adhafajri.moviecatalog.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogDataSource {

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(remoteData: RemoteDataSource): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteData)
            }
    }

    override fun getPopularMovies(): LiveData<List<CatalogEntity>> {
        val catalogResults = MutableLiveData<List<CatalogEntity>>()
        remoteDataSource.getPopularMovies(object : Callback<BaseResponse<MoviesResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<MoviesResponse>>,
                response: Response<BaseResponse<MoviesResponse>>,
            ) {
                val moviesResponse = response.body()?.results
                val catalogList = ArrayList<CatalogEntity>()
                catalogResults.run {
                    moviesResponse?.forEach { movie ->
                        with(movie) {
                            val catalog = CatalogEntity(
                                id,
                                Constant.MOVIE,
                                title,
                                "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                                overview
                            )
                            catalogList.add(catalog)
                        }
                    }
                    postValue(catalogList)
                }
            }

            override fun onFailure(call: Call<BaseResponse<MoviesResponse>>, t: Throwable) =
                t.printStackTrace()
        })
        return catalogResults
    }

    override fun getUpcomingMovies(): LiveData<List<CatalogEntity>> {
        val catalogResults = MutableLiveData<List<CatalogEntity>>()
        remoteDataSource.getUpcomingMovies(object : Callback<BaseResponse<MoviesResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<MoviesResponse>>,
                response: Response<BaseResponse<MoviesResponse>>,
            ) {
                val catalogList = ArrayList<CatalogEntity>()
                val moviesResponse = response.body()?.results
                catalogResults.run {
                    moviesResponse?.forEach { movie ->
                        with(movie) {
                            val catalog = CatalogEntity(
                                id,
                                Constant.MOVIE,
                                title,
                                "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                                overview
                            )
                            catalogList.add(catalog)
                        }
                    }
                    postValue(catalogList)
                }
            }

            override fun onFailure(call: Call<BaseResponse<MoviesResponse>>, t: Throwable) =
                t.printStackTrace()
        })
        return catalogResults
    }

    override fun getMovieDetails(movieId: String): LiveData<CatalogEntity> {
        val movieDetailsResult = MutableLiveData<CatalogEntity>()
        remoteDataSource.getMovieDetails(movieId, object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>,
            ) {
                val moviesResponse = response.body()
                val catalog = moviesResponse?.let {
                    with(it) {
                        CatalogEntity(
                            id,
                            Constant.MOVIE,
                            title,
                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                            overview
                        )
                    }
                }
                movieDetailsResult.postValue(catalog)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) = t.printStackTrace()
        })
        return movieDetailsResult
    }


    override fun getMovieCredits(movieId: String): LiveData<List<CreditEntity>> {
        val creditResults = MutableLiveData<List<CreditEntity>>()
        remoteDataSource.getMovieCredits(movieId, object : Callback<BaseResponse<CreditResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<CreditResponse>>,
                response: Response<BaseResponse<CreditResponse>>,
            ) {
                val creditList = ArrayList<PersonJobEntity>()
                val creditResponse = response.body()
                val creditsCrew = creditResponse?.crew
                creditsCrew?.forEach {
                    with(it) {
                        val person = PersonJobEntity(
                            id,
                            name,
                            job,
                        )
                        creditList.add(person)
                    }
                }

                val personList = ArrayList<CreditEntity>()
                creditList.groupBy { it.personId }.entries.map { (id, it) ->
                    personList.add(
                        CreditEntity(PersonEntity(id, it[0].name), it)
                    )
                }

                creditResults.postValue(personList)
            }

            override fun onFailure(call: Call<BaseResponse<CreditResponse>>, t: Throwable) =
                t.printStackTrace()
        })
        return creditResults
    }

    override fun getMovieVideo(movieId: String): LiveData<VideoEntity> {
        val movieVideoResult = MutableLiveData<VideoEntity>()
        remoteDataSource.getMovieVideos(movieId, object : Callback<BaseResponse<VideoResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<VideoResponse>>,
                response: Response<BaseResponse<VideoResponse>>,
            ) {
                val videoList = ArrayList<VideoEntity>()
                val videoResponse = response.body()?.results
                videoResponse?.forEach {
                    with(it) {
                        val video = VideoEntity(
                            id,
                            key,
                            site,
                        )
                        videoList.add(video)
                    }
                }

                if (videoList.isNotEmpty()) {
                    val youtubeVideos = videoList.find { it.site == Constant.SITE_YOUTUBE }
                    if (youtubeVideos != null) {
                        movieVideoResult.postValue(youtubeVideos)
                    } else {
                        movieVideoResult.postValue(videoList.first())
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<VideoResponse>>, t: Throwable) =
                t.printStackTrace()
        })
        return movieVideoResult
    }

    override fun getPopularTvShows(): LiveData<List<CatalogEntity>> {
        val catalogResults = MutableLiveData<List<CatalogEntity>>()
        remoteDataSource.getPopularTvShows(object : Callback<BaseResponse<TvShowResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<TvShowResponse>>,
                response: Response<BaseResponse<TvShowResponse>>,
            ) {
                val tvShowResponse = response.body()?.results
                val catalogList = ArrayList<CatalogEntity>()
                catalogResults.run {
                    tvShowResponse?.forEach {
                        with(it) {
                            val catalog = CatalogEntity(
                                id,
                                Constant.TV_SHOW,
                                name,
                                "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                                overview
                            )
                            catalogList.add(catalog)
                        }
                    }
                    postValue(catalogList)
                }
            }

            override fun onFailure(call: Call<BaseResponse<TvShowResponse>>, t: Throwable) =
                t.printStackTrace()
        })
        return catalogResults
    }

    override fun getTodayAiringTvShows(): LiveData<List<CatalogEntity>> {
        val catalogResults = MutableLiveData<List<CatalogEntity>>()
        remoteDataSource.getTodayAiringTvShows(object : Callback<BaseResponse<TvShowResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<TvShowResponse>>,
                response: Response<BaseResponse<TvShowResponse>>,
            ) {
                val tvShowResponse = response.body()?.results
                val catalogList = ArrayList<CatalogEntity>()
                catalogResults.run {
                    tvShowResponse?.forEach {
                        with(it) {
                            val catalog = CatalogEntity(
                                id,
                                Constant.TV_SHOW,
                                name,
                                "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                                overview
                            )
                            catalogList.add(catalog)
                        }
                    }
                    postValue(catalogList)
                }
            }

            override fun onFailure(call: Call<BaseResponse<TvShowResponse>>, t: Throwable) =
                t.printStackTrace()
        })
        return catalogResults
    }

    override fun getTvShowDetails(tvShowId: String): LiveData<CatalogEntity> {
        val tvShowDetailsResult = MutableLiveData<CatalogEntity>()
        remoteDataSource.getTvShowDetails(tvShowId, object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>,
            ) {
                val tvShowResponse = response.body()
                val catalog = tvShowResponse?.let {
                    with(it) {
                        CatalogEntity(
                            id,
                            Constant.MOVIE,
                            name,
                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                            overview
                        )
                    }
                }
                tvShowDetailsResult.postValue(catalog)
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) = t.printStackTrace()
        })
        return tvShowDetailsResult
    }

    override fun getTvShowCredits(tvShowId: String): LiveData<List<CreditEntity>> {
        val tvShowCreditResults = MutableLiveData<List<CreditEntity>>()
        remoteDataSource.getTvShowCredits(
            tvShowId,
            object : Callback<BaseResponse<CreditResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<CreditResponse>>,
                    response: Response<BaseResponse<CreditResponse>>,
                ) {
                    val creditList = ArrayList<PersonJobEntity>()
                    val creditResponse = response.body()
                    val creditsCrew = creditResponse?.crew
                    creditsCrew?.forEach {
                        with(it) {
                            val person = PersonJobEntity(
                                id,
                                name,
                                job,
                            )
                            creditList.add(person)
                        }
                    }

                    val personList = ArrayList<CreditEntity>()
                    creditList.groupBy { it.personId }.entries.map { (id, it) ->
                        personList.add(
                            CreditEntity(PersonEntity(id, it[0].name), it)
                        )
                    }

                    tvShowCreditResults.postValue(personList)
                }

                override fun onFailure(call: Call<BaseResponse<CreditResponse>>, t: Throwable) =
                    t.printStackTrace()
            })
        return tvShowCreditResults
    }

    override fun getTvShowVideo(tvShowId: String): LiveData<VideoEntity> {
        val tvShowVideoResult = MutableLiveData<VideoEntity>()
        remoteDataSource.getTvShowVideos(tvShowId, object : Callback<BaseResponse<VideoResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<VideoResponse>>,
                response: Response<BaseResponse<VideoResponse>>,
            ) {
                val videoList = ArrayList<VideoEntity>()
                val videoResponse = response.body()?.results
                videoResponse?.forEach {
                    with(it) {
                        val video = VideoEntity(
                            id,
                            key,
                            site,
                        )
                        videoList.add(video)
                    }
                }

                if (videoList.isNotEmpty()) {
                    val youtubeVideo = videoList.find { it.site == Constant.SITE_YOUTUBE }
                    if (youtubeVideo != null) {
                        tvShowVideoResult.postValue(youtubeVideo)
                    } else {
                        tvShowVideoResult.postValue(videoList.first())
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<VideoResponse>>, t: Throwable) =
                t.printStackTrace()
        })
        return tvShowVideoResult
    }

}