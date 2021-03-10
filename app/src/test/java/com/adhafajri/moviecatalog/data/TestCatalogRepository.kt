package com.adhafajri.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.source.local.LocalDataSource
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogWithPerson
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity
import com.adhafajri.moviecatalog.data.source.remote.ApiResponse
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.data.source.remote.response.CreditResponse
import com.adhafajri.moviecatalog.data.source.remote.response.MoviesResponse
import com.adhafajri.moviecatalog.data.source.remote.response.TvShowResponse
import com.adhafajri.moviecatalog.data.source.remote.response.VideoResponse
import com.adhafajri.moviecatalog.utils.AppExecutors
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.vo.Resource

class TestCatalogRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogDataSource {
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
                            Constant.POPULAR_MOVIES,
                            title,
                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                            overview
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
                remoteDataSource.getUpcomingMovies()

            override fun saveCallResult(data: List<MoviesResponse>) {
                val catalogList = ArrayList<CatalogEntity>()
                data.forEach {
                    with(it) {
                        val catalog = CatalogEntity(
                            id,
                            Constant.MOVIE,
                            Constant.UPCOMING_MOVIES,
                            title,
                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                            overview
                        )
                        catalogList.add(catalog)
                    }
                    localDataSource.insertCatalogs(catalogList)
                }
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CatalogEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<CatalogEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getPopularTvShowCatalogs(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<CatalogEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getPopularTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val catalogList = ArrayList<CatalogEntity>()
                data.forEach {
                    with(it) {
                        val catalog = CatalogEntity(
                            id,
                            Constant.TV_SHOW,
                            Constant.POPULAR_TV_SHOWS,
                            name,
                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                            overview
                        )
                        catalogList.add(catalog)
                    }
                    localDataSource.insertCatalogs(catalogList)
                }
            }
        }.asLiveData()
    }

    override fun getTodayAiringTvShows(): LiveData<Resource<PagedList<CatalogEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CatalogEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<CatalogEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getTodayAiringTvShowCatalogs(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<CatalogEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTodayAiringTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val catalogList = ArrayList<CatalogEntity>()
                data.forEach {
                    with(it) {
                        val catalog = CatalogEntity(
                            id,
                            Constant.TV_SHOW,
                            Constant.TODAY_AIRING_TV_SHOWS,
                            name,
                            "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                            overview
                        )
                        catalogList.add(catalog)
                    }
                    localDataSource.insertCatalogs(catalogList)
                }
            }
        }.asLiveData()
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
                data.forEach {
                    with(it) {
                        val person = PersonEntity(
                            id,
                            catalogId,
                            name
                        )
                        personList.add(person)
                    }

                    val persons = ArrayList<PersonEntity>()
                    personList.groupBy { person -> person.personId }.entries.map { person ->
                        persons.addAll(person.value)
                    }

                    localDataSource.insertPersons(persons)
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

                var videoUrl: String? = null
                if (videoSite != null && videoResponse != null) {
                    videoUrl = "$videoSite${videoResponse.key}"
                }

                videoUrl?.let { localDataSource.updateVideo(it, catalogId) }
            }
        }.asLiveData()
    }

    override fun getFavoriteCatalogs(): LiveData<PagedList<CatalogEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteCatalogs(), config).build()
    }

    override fun setCatalogFavorite(catalog: CatalogEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setCatalogFavorite(catalog, state) }
    }
}