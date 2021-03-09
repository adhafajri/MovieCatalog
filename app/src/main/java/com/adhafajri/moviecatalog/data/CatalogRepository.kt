//package com.adhafajri.moviecatalog.data
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.adhafajri.moviecatalog.data.source.local.entity.*
//import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
//import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
//import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
//import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
//import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse
//import com.adhafajri.moviecatalog.utils.Constant
//import java.util.*
//import kotlin.collections.ArrayList
//
//class CatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) :
//    CatalogDataSource {
//
//    companion object {
//        @Volatile
//        private var instance: CatalogRepository? = null
//
//        fun getInstance(remoteData: RemoteDataSource): CatalogRepository =
//            instance ?: synchronized(this) {
//                instance ?: CatalogRepository(remoteData)
//            }
//    }
//
//    override fun getPopularMovies(): LiveData<List<CatalogEntity>> {
//        val catalogResults = MutableLiveData<List<CatalogEntity>>()
//        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadMoviesCallback {
//            override fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>?) {
//                val catalogList = ArrayList<CatalogEntity>()
//                moviesResponse?.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.MOVIE,
//                            title,
//                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
//                            overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                }
//                catalogResults.postValue(catalogList)
//            }
//
//        })
//        return catalogResults
//    }
//
//    override fun getPopularTvShows(): LiveData<List<CatalogEntity>> {
//        val catalogResults = MutableLiveData<List<CatalogEntity>>()
//        remoteDataSource.getPopularTvShows(object : RemoteDataSource.LoadTvShowsCallback {
//            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>?) {
//                val catalogList = ArrayList<CatalogEntity>()
//                tvShowResponse?.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.TV_SHOW,
//                            name,
//                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
//                            overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                }
//                catalogResults.postValue(catalogList)
//            }
//        })
//        return catalogResults
//    }
//
//    override fun getUpcomingMovies(): LiveData<List<CatalogEntity>> {
//        val catalogResults = MutableLiveData<List<CatalogEntity>>()
//        remoteDataSource.getUpcomingMovies(object : RemoteDataSource.LoadMoviesCallback {
//            override fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>?) {
//                val catalogList = ArrayList<CatalogEntity>()
//                moviesResponse?.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.MOVIE,
//                            title,
//                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
//                            overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                }
//                catalogResults.postValue(catalogList)
//            }
//        })
//        return catalogResults
//    }
//
//    override fun getTodayAiringTvShows(): LiveData<List<CatalogEntity>> {
//        val catalogResults = MutableLiveData<List<CatalogEntity>>()
//        remoteDataSource.getTodayAiringTvShows(object : RemoteDataSource.LoadTvShowsCallback {
//            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>?) {
//                val catalogList = ArrayList<CatalogEntity>()
//                tvShowResponse?.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.TV_SHOW,
//                            name,
//                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
//                            overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                }
//                catalogResults.postValue(catalogList)
//            }
//        })
//        return catalogResults
//    }
//
//    override fun getMovieVideo(movieId: String): LiveData<VideoEntity> {
//        val videoResult = MutableLiveData<VideoEntity>()
//        remoteDataSource.getMovieVideos(movieId, object : RemoteDataSource.LoadVideosCallback {
//            override fun onAllVideosReceived(videosResponse: List<VideoResponse>?) {
//                val videoList = ArrayList<VideoEntity>()
//                videosResponse?.forEach {
//                    with(it) {
//                        val video = VideoEntity(
//                            id,
//                            key,
//                            site
//                        )
//                        videoList.add(video)
//                    }
//                }
//
//                val youtubeVideo = videoList.find { it.site == Constant.SITE_YOUTUBE }
//                videoResult.postValue(youtubeVideo ?: videoList.first())
//            }
//        })
//        return videoResult
//    }
//
//    override fun getTvShowVideo(tvShowId: String): LiveData<VideoEntity> {
//        val videoResult = MutableLiveData<VideoEntity>()
//        remoteDataSource.getTvShowVideos(tvShowId, object : RemoteDataSource.LoadVideosCallback {
//            override fun onAllVideosReceived(videosResponse: List<VideoResponse>?) {
//                val videoList = ArrayList<VideoEntity>()
//                videosResponse?.forEach {
//                    with(it) {
//                        val video = VideoEntity(
//                            id,
//                            key,
//                            site
//                        )
//                        videoList.add(video)
//                    }
//                }
//
//                val youtubeVideo = videoList.find { it.site == Constant.SITE_YOUTUBE }
//                videoResult.postValue(youtubeVideo ?: videoList.first())
//            }
//        })
//        return videoResult
//    }
//
//    override fun getMovieCredits(movieId: String): LiveData<List<CreditEntity>> {
//        val creditResults = MutableLiveData<List<CreditEntity>>()
//        remoteDataSource.getMovieCredits(movieId, object : RemoteDataSource.LoadCreditsCallback {
//            override fun onAllCreditsReceived(creditResponse: List<CreditResponse>?) {
//                val personsList = ArrayList<PersonEntity>()
//                val jobList = ArrayList<JobEntity>()
//                creditResponse?.forEach {
//                    with(it) {
//                        val person = PersonEntity(
//                            id,
//                            name
//                        )
//                        personsList.add(person)
//
//                        val job = JobEntity(
//                            "job${id}",
//                            id,
//                            job
//                        )
//                        jobList.add(job)
//                    }
//                }
//
//                val personWithJobList = ArrayList<PersonWithJob>()
//                jobList.groupBy { it.personId }.entries.map { (id, it) ->
//                    personWithJobList.add(
//                        PersonWithJob(PersonEntity(id, it.first().), it)
//                    )
//                }
//
//                creditResults.postValue(creditList)
//            }
//        })
//        return creditResults
//    }
//
//    override fun getTvShowCredits(tvShowId: String): LiveData<List<CreditEntity>> {
//        val creditResults = MutableLiveData<List<CreditEntity>>()
//        remoteDataSource.getTvShowCredits(tvShowId, object : RemoteDataSource.LoadCreditsCallback {
//            override fun onAllCreditsReceived(creditResponse: List<CreditResponse>?) {
//                val personsJobList = ArrayList<PersonJobEntity>()
//                creditResponse?.forEach {
//                    with(it) {
//                        val person = PersonJobEntity(
//                            id,
//                            name,
//                            job
//                        )
//                        personsJobList.add(person)
//                    }
//                }
//
//                val creditList = ArrayList<CreditEntity>()
//                personsJobList.groupBy { it.personId }.entries.map { (id, it) ->
//                    creditList.add(
//                        CreditEntity(id, PersonEntity(id, it.first().name), it)
//                    )
//                }
//
//                creditResults.postValue(creditList)
//            }
//        })
//        return creditResults
//    }
//}

package com.adhafajri.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.source.local.LocalDataSource
import com.adhafajri.moviecatalog.data.source.local.entity.*
import com.adhafajri.moviecatalog.data.source.remote.ApiResponse
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse
import com.adhafajri.moviecatalog.utils.AppExecutors
import com.adhafajri.moviecatalog.utils.Constant
import com.dicoding.academies.vo.Resource

class CatalogRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CatalogDataSource {

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getPopularMovies(): LiveData<Resource<PagedList<CatalogEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CatalogEntity>, List<MoviesResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<CatalogEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getPopularMovieCatalogs(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<CatalogEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getPopularMovies()

            override fun saveCallResult(data: List<MoviesResponse>) {
                val catalogList = ArrayList<CatalogEntity>()
                data.forEach {
                    with(it) {
                        val catalog = CatalogEntity(
                            id,
                            Constant.MOVIE,
                            Constant.UPCOMING_MOVIES,
                            title,
                            posterPath, overview
                        )
                        catalogList.add(catalog)
                    }
                    localDataSource.insertCatalogs(catalogList)
                }
            }
        }.asLiveData()
    }

    override fun getUpcomingMovies(): LiveData<Resource<PagedList<CatalogEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CatalogEntity>, List<MoviesResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<CatalogEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getUpcomingMovieCatalogs(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<CatalogEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getPopularMovies()

            override fun saveCallResult(data: List<MoviesResponse>) {
                val catalogList = ArrayList<CatalogEntity>()
                data.forEach {
                    with(it) {
                        val catalog = CatalogEntity(
                            id,
                            Constant.MOVIE,
                            Constant.UPCOMING_MOVIES,
                            title,
                            posterPath, overview
                        )
                        catalogList.add(catalog)
                    }
                    localDataSource.insertCatalogs(catalogList)
                }
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> {
        TODO("Not yet implemented")
    }

    override fun getTodayAiringTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> {
        TODO("Not yet implemented")
    }

    override fun getCatalogWithPersons(
        catalogId: String,
        type: String
    ): LiveData<Resource<CatalogWithPerson>> {
        return object :
            NetworkBoundResource<CatalogWithPerson, List<CreditResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<CatalogWithPerson> =
                localDataSource.getCatalogWithPersons(catalogId)

            override fun shouldFetch(data: CatalogWithPerson?): Boolean =
                data?.persons == null || data.persons.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CreditResponse>>> {
                return if (type == Constant.MOVIE) {
                    remoteDataSource.getMovieCredits(catalogId)
                } else {
                    remoteDataSource.getTvShowCredits(catalogId)
                }
            }

            override fun saveCallResult(data: List<CreditResponse>) {
                val personList = ArrayList<PersonEntity>()
                val jobList = ArrayList<JobEntity>()
                data.forEach {
                    with(it) {
                        val person = PersonEntity(
                            id,
                            catalogId,
                            name
                        )
                        personList.add(person)
                        val job = JobEntity(
                            "job$id",
                            id,
                            job
                        )
                        jobList.add(job)
                    }
                    localDataSource.insertPersons(personList)
                    localDataSource.insertJobs(jobList)
                }
            }
        }.asLiveData()
    }

    override fun getAllPersonByCatalogId(
        catalogId: String,
        type: String
    ): LiveData<Resource<List<PersonEntity>>> {
        return object :
            NetworkBoundResource<List<PersonEntity>, List<CreditResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<PersonEntity>> =
                localDataSource.getAllPersonsByCatalogId(catalogId)

            override fun shouldFetch(data: List<PersonEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CreditResponse>>> {
                return if (type == Constant.MOVIE) {
                    remoteDataSource.getMovieCredits(catalogId)
                } else {
                    remoteDataSource.getTvShowCredits(catalogId)
                }
            }

            override fun saveCallResult(data: List<CreditResponse>) {
                val personList = ArrayList<PersonEntity>()
                val jobList = ArrayList<JobEntity>()
                data.forEach {
                    with(it) {
                        val person = PersonEntity(
                            id,
                            catalogId,
                            name
                        )
                        personList.add(person)
                        val job = JobEntity(
                            "job$id",
                            id,
                            job
                        )
                        jobList.add(job)
                    }
                    localDataSource.insertPersons(personList)
                    localDataSource.insertJobs(jobList)
                }
            }
        }.asLiveData()
    }

    override fun getPersonWithJobs(
        catalogId: String,
        personId: String,
        type: String
    ): LiveData<Resource<PersonWithJob>> {
        return object :
            NetworkBoundResource<PersonWithJob, List<CreditResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PersonWithJob> =
                localDataSource.getPersonWithJobs(personId)

            override fun shouldFetch(data: PersonWithJob?): Boolean =
                data?.jobs == null || data.jobs.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CreditResponse>>> {
                return if (type == Constant.MOVIE) {
                    remoteDataSource.getMovieCredits(catalogId)
                } else {
                    remoteDataSource.getTvShowCredits(catalogId)
                }
            }

            override fun saveCallResult(data: List<CreditResponse>) {
                val personList = ArrayList<PersonEntity>()
                val jobList = ArrayList<JobEntity>()
                data.forEach {
                    with(it) {
                        val person = PersonEntity(
                            id,
                            catalogId,
                            name
                        )
                        personList.add(person)
                        val job = JobEntity(
                            "job$id",
                            id,
                            job
                        )
                        jobList.add(job)
                    }
                    localDataSource.insertPersons(personList)
                    localDataSource.insertJobs(jobList)
                }
            }
        }.asLiveData()
    }

    override fun getVideo(catalogId: String, type: String): LiveData<Resource<CatalogEntity>> {
        return object : NetworkBoundResource<CatalogEntity, List<VideoResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<CatalogEntity> =
                localDataSource.getCatalogWithVideo(catalogId)

            override fun shouldFetch(data: CatalogEntity?): Boolean =
                data?.videoEntity == null

            override fun createCall(): LiveData<ApiResponse<List<VideoResponse>>> {
                return if (type == Constant.MOVIE) {
                    remoteDataSource.getMovieVideos(catalogId)
                } else {
                    remoteDataSource.getTvShowVideos(catalogId)
                }
            }

            override fun saveCallResult(data: List<VideoResponse>) {
                val videoResponse = data.find { it.type == Constant.TRAILER }
                val videoSite = when (videoResponse?.site) {
                    Constant.SITE_YOUTUBE -> Constant.YOUTUBE_VIDEO_URL
                    Constant.SITE_VIMEO -> Constant.VIMEO_VIDEO_URL
                    else -> null
                }

                val videoUrl = "$videoSite${videoResponse?.key}"
                localDataSource.updateVideo(videoUrl, catalogId)
            }
        }.asLiveData()
    }

    override fun getFavoriteCatalogs(): LiveData<PagedList<CatalogEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteCatalogs(), config).build()
    }

    override fun setCatalogFavorite(catalog: CatalogEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setCatalogFavorite(catalog, state) }


//    override fun getMovieCredits(movieId: String): LiveData<Resource<PagedList<CreditEntity>>> {
//        return object :
//            NetworkBoundResource<PagedList<PersonEntity>, List<CreditResponse>>(appExecutors)  {
//            override fun loadFromDB(): LiveData<PagedList<PersonEntity>> {
//                val config = PagedList.Config.Builder()
//                    .setEnablePlaceholders(false)
//                    .setInitialLoadSizeHint(4)
//                    .setPageSize(4)
//                    .build()
//                return LivePagedListBuilder(
//                    localDataSource.getPersonWithJobs(),
//                    config
//                ).build()
//            }
//
//            override fun shouldFetch(data: PagedList<CatalogEntity>?): Boolean =
//                data == null || data.isEmpty()
//
//            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> =
//                remoteDataSource.getPopularMovies()
//
//            override fun saveCallResult(data: List<MoviesResponse>) {
//                val catalogList = ArrayList<CatalogEntity>()
//                data.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.MOVIE,
//                            Constant.UPCOMING_MOVIES,
//                            title,
//                            posterPath, overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                    localDataSource.insertCatalogs(catalogList)
//                }
//            }
//        }.asLiveData()
//    }
//
//    override fun getMovieVideo(movieId: String): LiveData<Resource<VideoEntity>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getPopularTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTodayAiringTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTvShowCredits(tvShowId: String): LiveData<Resource<PagedList<CreditEntity>>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTvShowVideo(tvShowId: String): LiveData<Resource<VideoEntity>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getFavoritedCatalogs(): LiveData<PagedList<CatalogEntity>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun setCatalogFavorite(catalog: CatalogEntity, state: Boolean) {
//        TODO("Not yet implemented")
//    }

//    override fun getPopularTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> {
//        val catalogResults = MutableLiveData<List<CatalogEntity>>()
//        remoteDataSource.getPopularTvShows(object : RemoteDataSource.LoadTvShowsCallback {
//            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>?) {
//                val catalogList = ArrayList<CatalogEntity>()
//                tvShowResponse?.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.TV_SHOW,
//                            name,
//                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
//                            overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                }
//                catalogResults.postValue(catalogList)
//            }
//        })
//        return catalogResults
//    }
//
//    override fun getUpcomingMovies(): LiveData<List<CatalogEntity>> {
//        val catalogResults = MutableLiveData<List<CatalogEntity>>()
//        remoteDataSource.getUpcomingMovies(object : RemoteDataSource.LoadMoviesCallback {
//            override fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>?) {
//                val catalogList = ArrayList<CatalogEntity>()
//                moviesResponse?.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.MOVIE,
//                            title,
//                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
//                            overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                }
//                catalogResults.postValue(catalogList)
//            }
//        })
//        return catalogResults
//    }
//
//    override fun getTodayAiringTvShows(): LiveData<List<CatalogEntity>> {
//        val catalogResults = MutableLiveData<List<CatalogEntity>>()
//        remoteDataSource.getTodayAiringTvShows(object : RemoteDataSource.LoadTvShowsCallback {
//            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>?) {
//                val catalogList = ArrayList<CatalogEntity>()
//                tvShowResponse?.forEach {
//                    with(it) {
//                        val catalog = CatalogEntity(
//                            id,
//                            Constant.TV_SHOW,
//                            name,
//                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
//                            overview
//                        )
//                        catalogList.add(catalog)
//                    }
//                }
//                catalogResults.postValue(catalogList)
//            }
//        })
//        return catalogResults
//    }
//
//    override fun getMovieVideo(movieId: String): LiveData<VideoEntity> {
//        val videoResult = MutableLiveData<VideoEntity>()
//        remoteDataSource.getMovieVideos(movieId, object : RemoteDataSource.LoadVideosCallback {
//            override fun onAllVideosReceived(videosResponse: List<VideoResponse>?) {
//                val videoList = ArrayList<VideoEntity>()
//                videosResponse?.forEach {
//                    with(it) {
//                        val video = VideoEntity(
//                            id,
//                            key,
//                            site
//                        )
//                        videoList.add(video)
//                    }
//                }
//
//                val youtubeVideo = videoList.find { it.site == Constant.SITE_YOUTUBE }
//                videoResult.postValue(youtubeVideo ?: videoList.first())
//            }
//        })
//        return videoResult
//    }
//
//    override fun getTvShowVideo(tvShowId: String): LiveData<VideoEntity> {
//        val videoResult = MutableLiveData<VideoEntity>()
//        remoteDataSource.getTvShowVideos(tvShowId, object : RemoteDataSource.LoadVideosCallback {
//            override fun onAllVideosReceived(videosResponse: List<VideoResponse>?) {
//                val videoList = ArrayList<VideoEntity>()
//                videosResponse?.forEach {
//                    with(it) {
//                        val video = VideoEntity(
//                            id,
//                            key,
//                            site
//                        )
//                        videoList.add(video)
//                    }
//                }
//
//                val youtubeVideo = videoList.find { it.site == Constant.SITE_YOUTUBE }
//                videoResult.postValue(youtubeVideo ?: videoList.first())
//            }
//        })
//        return videoResult
//    }
//
//    override fun getMovieCredits(movieId: String): LiveData<List<CreditEntity>> {
//        val creditResults = MutableLiveData<List<CreditEntity>>()
//        remoteDataSource.getMovieCredits(movieId, object : RemoteDataSource.LoadCreditsCallback {
//            override fun onAllCreditsReceived(creditResponse: List<CreditResponse>?) {
//                val personsList = ArrayList<PersonEntity>()
//                val jobList = ArrayList<JobEntity>()
//                creditResponse?.forEach {
//                    with(it) {
//                        val person = PersonEntity(
//                            id,
//                            name
//                        )
//                        personsList.add(person)
//
//                        val job = JobEntity(
//                            "job${id}",
//                            id,
//                            job
//                        )
//                        jobList.add(job)
//                    }
//                }
//
//                val personWithJobList = ArrayList<PersonWithJob>()
//                jobList.groupBy { it.personId }.entries.map { (id, it) ->
//                    personWithJobList.add(
//                        PersonWithJob(PersonEntity(id, it.first().), it)
//                    )
//                }
//
//                creditResults.postValue(creditList)
//            }
//        })
//        return creditResults
//    }
//
//    override fun getTvShowCredits(tvShowId: String): LiveData<List<CreditEntity>> {
//        val creditResults = MutableLiveData<List<CreditEntity>>()
//        remoteDataSource.getTvShowCredits(tvShowId, object : RemoteDataSource.LoadCreditsCallback {
//            override fun onAllCreditsReceived(creditResponse: List<CreditResponse>?) {
//                val personsJobList = ArrayList<PersonJobEntity>()
//                creditResponse?.forEach {
//                    with(it) {
//                        val person = PersonJobEntity(
//                            id,
//                            name,
//                            job
//                        )
//                        personsJobList.add(person)
//                    }
//                }
//
//                val creditList = ArrayList<CreditEntity>()
//                personsJobList.groupBy { it.personId }.entries.map { (id, it) ->
//                    creditList.add(
//                        CreditEntity(id, PersonEntity(id, it.first().name), it)
//                    )
//                }
//
//                creditResults.postValue(creditList)
//            }
//        })
//        return creditResults
//    }
}