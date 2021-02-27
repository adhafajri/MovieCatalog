package com.adhafajri.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhafajri.moviecatalog.data.source.local.entity.*
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse
import com.adhafajri.moviecatalog.utils.Constant

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
        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>?) {
                val catalogList = ArrayList<CatalogEntity>()
                moviesResponse?.forEach {
                    with(it) {
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
                catalogResults.postValue(catalogList)
            }

        })
        return catalogResults
    }

    override fun getPopularTvShows(): LiveData<List<CatalogEntity>> {
        val catalogResults = MutableLiveData<List<CatalogEntity>>()
        remoteDataSource.getPopularTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>?) {
                val catalogList = ArrayList<CatalogEntity>()
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
                catalogResults.postValue(catalogList)
            }
        })
        return catalogResults
    }

    override fun getUpcomingMovies(): LiveData<List<CatalogEntity>> {
        val catalogResults = MutableLiveData<List<CatalogEntity>>()
        remoteDataSource.getUpcomingMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>?) {
                val catalogList = ArrayList<CatalogEntity>()
                moviesResponse?.forEach {
                    with(it) {
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
                catalogResults.postValue(catalogList)
            }
        })
        return catalogResults
    }

    override fun getTodayAiringTvShows(): LiveData<List<CatalogEntity>> {
        val catalogResults = MutableLiveData<List<CatalogEntity>>()
        remoteDataSource.getTodayAiringTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>?) {
                val catalogList = ArrayList<CatalogEntity>()
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
                catalogResults.postValue(catalogList)
            }
        })
        return catalogResults
    }

    override fun getMovieVideo(movieId: String): LiveData<VideoEntity> {
        val videoResult = MutableLiveData<VideoEntity>()
        remoteDataSource.getMovieVideos(movieId, object : RemoteDataSource.LoadVideosCallback {
            override fun onAllVideosReceived(videosResponse: List<VideoResponse>?) {
                val videoList = ArrayList<VideoEntity>()
                videosResponse?.forEach {
                    with(it) {
                        val video = VideoEntity(
                            id,
                            key,
                            site
                        )
                        videoList.add(video)
                    }
                }

                val youtubeVideo = videoList.find { it.site == Constant.SITE_YOUTUBE }
                videoResult.postValue(youtubeVideo ?: videoList.first())
            }
        })
        return videoResult
    }

    override fun getTvShowVideo(tvShowId: String): LiveData<VideoEntity> {
        val videoResult = MutableLiveData<VideoEntity>()
        remoteDataSource.getTvShowVideos(tvShowId, object : RemoteDataSource.LoadVideosCallback {
            override fun onAllVideosReceived(videosResponse: List<VideoResponse>?) {
                val videoList = ArrayList<VideoEntity>()
                videosResponse?.forEach {
                    with(it) {
                        val video = VideoEntity(
                            id,
                            key,
                            site
                        )
                        videoList.add(video)
                    }
                }

                val youtubeVideo = videoList.find { it.site == Constant.SITE_YOUTUBE }
                videoResult.postValue(youtubeVideo ?: videoList.first())
            }
        })
        return videoResult
    }

    override fun getMovieCredits(movieId: String): LiveData<List<CreditEntity>> {
        val creditResults = MutableLiveData<List<CreditEntity>>()
        remoteDataSource.getMovieCredits(movieId, object : RemoteDataSource.LoadCreditsCallback {
            override fun onAllCreditsReceived(creditResponse: List<CreditResponse>?) {
                val personsJobList = ArrayList<PersonJobEntity>()
                creditResponse?.forEach {
                    with(it) {
                        val person = PersonJobEntity(
                            id,
                            name,
                            job
                        )
                        personsJobList.add(person)
                    }
                }

                val creditList = ArrayList<CreditEntity>()
                personsJobList.groupBy { it.personId }.entries.map { (id, it) ->
                    creditList.add(
                        CreditEntity(id, PersonEntity(id, it.first().name), it)
                    )
                }

                creditResults.postValue(creditList)
            }
        })
        return creditResults
    }

    override fun getTvShowCredits(tvShowId: String): LiveData<List<CreditEntity>> {
        val creditResults = MutableLiveData<List<CreditEntity>>()
        remoteDataSource.getTvShowCredits(tvShowId, object : RemoteDataSource.LoadCreditsCallback {
            override fun onAllCreditsReceived(creditResponse: List<CreditResponse>?) {
                val personsJobList = ArrayList<PersonJobEntity>()
                creditResponse?.forEach {
                    with(it) {
                        val person = PersonJobEntity(
                            id,
                            name,
                            job
                        )
                        personsJobList.add(person)
                    }
                }

                val creditList = ArrayList<CreditEntity>()
                personsJobList.groupBy { it.personId }.entries.map { (id, it) ->
                    creditList.add(
                        CreditEntity(id, PersonEntity(id, it.first().name), it)
                    )
                }

                creditResults.postValue(creditList)
            }
        })
        return creditResults
    }
}