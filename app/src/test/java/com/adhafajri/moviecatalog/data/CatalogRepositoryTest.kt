package com.adhafajri.moviecatalog.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adhafajri.moviecatalog.data.source.local.LocalDataSource
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogWithPerson
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity
import com.adhafajri.moviecatalog.data.source.local.entity.VideoEntity
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.utils.AppExecutors
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.LiveDataTestUtil
import com.adhafajri.moviecatalog.utils.PagedListUtil
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIHelper
import com.adhafajri.moviecatalog.utils.api.APIInterface
import com.adhafajri.moviecatalog.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val apiHelper = APIHelper(
        APIClient().getClient().create(
            APIInterface::class.java
        )
    )

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogRepository = TestCatalogRepository(remote, local, appExecutors)

    private val popularMovieResponse = apiHelper.getPopularMovies()
    private val popularMovieId = popularMovieResponse.first().id

    private val upcomingMovieResponse = apiHelper.getUpcomingMovies()

    private val popularTvShowResponse = apiHelper.getPopularTvShows()
    private val popularTvShowId = popularTvShowResponse.first().id

    private val todayAiringTvShowResponse = apiHelper.getTodayAiringTvShows()

    private val movieCreditResponse =
        apiHelper.getMovieCredits(popularMovieId)

    private val tvShowCreditResponse =
        apiHelper.getTvShowCredits(popularTvShowId)

    private val movieVideosResponse = apiHelper.getMovieVideos(popularMovieId)

    private val tvShowVideosResponse =
        apiHelper.getTvShowsVideos(popularTvShowId)

    @Test
    fun getPopularMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogEntity>
        `when`(local.getPopularMovieCatalogs()).thenReturn(dataSourceFactory)
        catalogRepository.getPopularMovies()

        val catalogEntities =
            Resource.success(PagedListUtil.mockPagedList(apiHelper.getPopularMovies()))
        verify(local).getPopularMovieCatalogs()
        assertNotNull(catalogEntities.data)
        assertEquals(popularMovieResponse.size.toLong(), catalogEntities.data?.size?.toLong())
    }

    @Test
    fun getUpcomingMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogEntity>
        `when`(local.getUpcomingMovieCatalogs()).thenReturn(dataSourceFactory)
        catalogRepository.getUpcomingMovies()

        val catalogEntities =
            Resource.success(PagedListUtil.mockPagedList(apiHelper.getUpcomingMovies()))
        verify(local).getUpcomingMovieCatalogs()
        assertNotNull(catalogEntities.data)
        assertEquals(upcomingMovieResponse.size.toLong(), catalogEntities.data?.size?.toLong())
    }

    @Test
    fun getPopularTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogEntity>
        `when`(local.getPopularTvShowCatalogs()).thenReturn(dataSourceFactory)
        catalogRepository.getPopularTvShows()

        val catalogEntities =
            Resource.success(PagedListUtil.mockPagedList(apiHelper.getPopularTvShows()))
        verify(local).getPopularTvShowCatalogs()
        assertNotNull(catalogEntities.data)
        assertEquals(popularTvShowResponse.size.toLong(), catalogEntities.data?.size?.toLong())
    }

    @Test
    fun getTodayAiringTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogEntity>
        `when`(local.getTodayAiringTvShowCatalogs()).thenReturn(dataSourceFactory)
        catalogRepository.getTodayAiringTvShows()

        val catalogEntities =
            Resource.success(PagedListUtil.mockPagedList(apiHelper.getTodayAiringTvShows()))
        verify(local).getTodayAiringTvShowCatalogs()
        assertNotNull(catalogEntities.data)
        assertEquals(todayAiringTvShowResponse.size.toLong(), catalogEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieCatalogWithPersons() {
        val dummyEntity = MutableLiveData<CatalogWithPerson>()

        var catalog: CatalogEntity
        with(popularMovieResponse.first()) {
            catalog = CatalogEntity(id, Constant.MOVIE, null, title, posterPath, overview)
        }

        val catalogId = catalog.catalogId

        val personList = ArrayList<PersonEntity>()
        movieCreditResponse.forEach {
            with(it) {
                val person = PersonEntity(
                    id, catalogId, name
                )
                personList.add(person)
            }
        }

        val catalogWithPerson = CatalogWithPerson(
            catalog,
            personList
        )

        dummyEntity.value =
            catalogWithPerson
        `when`<LiveData<CatalogWithPerson>>(local.getCatalogWithPersons(catalogId)).thenReturn(
            dummyEntity
        )

        val catalogEntities =
            LiveDataTestUtil.getValue(
                catalogRepository.getCatalogWithPersons(
                    catalogId,
                    Constant.MOVIE
                )
            )
        verify(local).getCatalogWithPersons(catalogId)
        assertNotNull(catalogEntities.data)
        assertNotNull(catalogEntities.data?.catalog?.title)
        assertEquals(catalog.title, catalogEntities.data?.catalog?.title)
    }

    @Test
    fun getTvShowCatalogWithPersons() {
        val dummyEntity = MutableLiveData<CatalogWithPerson>()

        var catalog: CatalogEntity
        with(popularTvShowResponse.first()) {
            catalog = CatalogEntity(id, Constant.TV_SHOW, null, name, posterPath, overview)
        }

        val catalogId = catalog.catalogId

        val personList = ArrayList<PersonEntity>()
        tvShowCreditResponse.forEach {
            with(it) {
                val person = PersonEntity(
                    id, catalogId, name
                )
                personList.add(person)
            }
        }

        val catalogWithPerson = CatalogWithPerson(
            catalog,
            personList
        )

        dummyEntity.value =
            catalogWithPerson
        `when`<LiveData<CatalogWithPerson>>(local.getCatalogWithPersons(catalogId)).thenReturn(
            dummyEntity
        )

        val catalogEntities =
            LiveDataTestUtil.getValue(
                catalogRepository.getCatalogWithPersons(
                    catalogId,
                    Constant.TV_SHOW
                )
            )
        verify(local).getCatalogWithPersons(catalogId)
        assertNotNull(catalogEntities.data)
        assertNotNull(catalogEntities.data?.catalog?.title)
        assertEquals(catalog.title, catalogEntities.data?.catalog?.title)
    }


    @Test
    fun getMovieCatalogVideo() {
        val dummyEntity = MutableLiveData<CatalogEntity>()

        var catalog: CatalogEntity
        with(popularMovieResponse.first()) {
            catalog = CatalogEntity(id, Constant.MOVIE, null, title, posterPath, overview)
        }

        val catalogId = catalog.catalogId

        val videoResponse = movieVideosResponse.find { it.type == Constant.TRAILER }
        val videoSite = when (videoResponse?.site) {
            Constant.SITE_YOUTUBE -> Constant.YOUTUBE_VIDEO_URL
            Constant.SITE_VIMEO -> Constant.VIMEO_VIDEO_URL
            else -> null
        }

        if (videoSite != null && videoResponse != null) {
            catalog.videoEntity = VideoEntity("$videoSite${videoResponse.key}")
        }

        dummyEntity.value = catalog
        `when`<LiveData<CatalogEntity>>(local.getCatalogWithVideo(catalogId)).thenReturn(
            dummyEntity
        )

        val catalogEntitiesVideo =
            LiveDataTestUtil.getValue(catalogRepository.getVideo(catalogId, Constant.MOVIE))
        verify(local).getCatalogWithVideo(catalogId)
        assertNotNull(catalogEntitiesVideo)
        assertNotNull(catalogEntitiesVideo.data?.videoEntity)
        assertNotNull(catalogEntitiesVideo.data?.videoEntity?.videoUrl)
        assertEquals(
            catalog.videoEntity?.videoUrl,
            catalogEntitiesVideo.data?.videoEntity?.videoUrl
        )
    }

    @Test
    fun getTvShowCatalogVideo() {
        val dummyEntity = MutableLiveData<CatalogEntity>()

        var catalog: CatalogEntity
        with(popularTvShowResponse.first()) {
            catalog = CatalogEntity(id, Constant.TV_SHOW, null, name, posterPath, overview)
        }

        val catalogId = catalog.catalogId

        val videoResponse = tvShowVideosResponse.find { it.type == Constant.TRAILER }
        val videoSite = when (videoResponse?.site) {
            Constant.SITE_YOUTUBE -> Constant.YOUTUBE_VIDEO_URL
            Constant.SITE_VIMEO -> Constant.VIMEO_VIDEO_URL
            else -> null
        }

        if (videoSite != null && videoResponse != null) {
            catalog.videoEntity = VideoEntity("$videoSite${videoResponse.key}")
        }

        dummyEntity.value = catalog
        `when`<LiveData<CatalogEntity>>(local.getCatalogWithVideo(catalogId)).thenReturn(
            dummyEntity
        )

        val catalogEntitiesVideo =
            LiveDataTestUtil.getValue(catalogRepository.getVideo(catalogId, Constant.TV_SHOW))
        verify(local).getCatalogWithVideo(catalogId)
        assertNotNull(catalogEntitiesVideo)
        assertNotNull(catalogEntitiesVideo.data?.videoEntity)
        assertNotNull(catalogEntitiesVideo.data?.videoEntity?.videoUrl)
        assertEquals(
            catalog.videoEntity?.videoUrl,
            catalogEntitiesVideo.data?.videoEntity?.videoUrl
        )
    }

    @Test
    fun getFavoriteCatalogs() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogEntity>
        `when`(local.getFavoriteCatalogs()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteCatalogs()

        val catalogEntities =
            Resource.success(PagedListUtil.mockPagedList(apiHelper.getPopularMovies()))
        verify(local).getFavoriteCatalogs()
        assertNotNull(catalogEntities.data)
        assertEquals(popularMovieResponse.size.toLong(), catalogEntities.data?.size?.toLong())
    }
}