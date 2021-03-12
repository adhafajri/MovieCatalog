package com.adhafajri.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.LocalDataSource
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogWithPerson
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity
import com.adhafajri.moviecatalog.data.source.local.entity.VideoEntity
import com.adhafajri.moviecatalog.data.source.local.room.CatalogDao
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIHelper
import com.adhafajri.moviecatalog.utils.api.APIInterface
import com.adhafajri.moviecatalog.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private lateinit var apiHelper: APIHelper

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var catalogWithPersonObserver: Observer<Resource<CatalogWithPerson>>

    @Mock
    private lateinit var catalogObserver: Observer<Resource<CatalogEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
        apiHelper = APIHelper(
            APIClient().getClient().create(
                APIInterface::class.java
            )
        )
    }

    @Test
    fun getMovieCatalogWithPersons() {
        val movieResponse = apiHelper.getPopularMovies().first()
        var catalogEntity: CatalogEntity
        with(movieResponse) {
            catalogEntity = CatalogEntity(
                id, Constant.MOVIE, null, title, posterPath, overview
            )
        }

        viewModel.setSelectedCatalog(catalogEntity.catalogId, Constant.MOVIE)

        val creditResponse = apiHelper.getMovieCredits(catalogEntity.catalogId)
        val personList = ArrayList<PersonEntity>()
        creditResponse.forEach {
            with(it) {
                val person = PersonEntity(
                    id, catalogEntity.catalogId, name
                )
                personList.add(person)
            }
        }


        val catalogWithPerson = Resource.success(
            CatalogWithPerson(
                catalogEntity,
                personList
            )
        )

        val catalog = MutableLiveData<Resource<CatalogWithPerson>>()
        catalog.value = catalogWithPerson

        `when`(
            catalogRepository.getCatalogWithPersons(
                catalogEntity.catalogId,
                Constant.MOVIE
            )
        ).thenReturn(catalog)

        viewModel.catalogPerson.observeForever(catalogWithPersonObserver)

        verify(catalogWithPersonObserver).onChanged(catalogWithPerson)
    }

    @Test
    fun getTvShowCatalogWithPersons() {
        val tvShowResponse = apiHelper.getPopularTvShows().first()
        var catalogEntity: CatalogEntity
        with(tvShowResponse) {
            catalogEntity = CatalogEntity(
                id, Constant.MOVIE, null, name, posterPath, overview
            )
        }

        viewModel.setSelectedCatalog(catalogEntity.catalogId, Constant.TV_SHOW)

        val creditResponse = apiHelper.getTvShowCredits(catalogEntity.catalogId)
        val personList = ArrayList<PersonEntity>()
        creditResponse.forEach {
            with(it) {
                val person = PersonEntity(
                    id, catalogEntity.catalogId, name
                )
                personList.add(person)
            }
        }


        val catalogWithPerson = Resource.success(
            CatalogWithPerson(
                catalogEntity,
                personList
            )
        )

        val catalog = MutableLiveData<Resource<CatalogWithPerson>>()
        catalog.value = catalogWithPerson

        `when`(
            catalogRepository.getCatalogWithPersons(
                catalogEntity.catalogId,
                Constant.TV_SHOW
            )
        ).thenReturn(catalog)

        viewModel.catalogPerson.observeForever(catalogWithPersonObserver)

        verify(catalogWithPersonObserver).onChanged(catalogWithPerson)
    }

    @Test
    fun getMovieVideo() {
        val movieResponse = apiHelper.getPopularMovies().first()
        var catalogEntity: CatalogEntity
        with(movieResponse) {
            catalogEntity = CatalogEntity(
                id, Constant.MOVIE, null, title, posterPath, overview
            )
        }

        viewModel.setSelectedCatalog(catalogEntity.catalogId, Constant.MOVIE)

        val videoResponse =
            apiHelper.getMovieVideos(catalogEntity.catalogId).find { it.type == Constant.TRAILER }
        val videoSite = when (videoResponse?.site) {
            Constant.SITE_YOUTUBE -> Constant.YOUTUBE_VIDEO_URL
            Constant.SITE_VIMEO -> Constant.VIMEO_VIDEO_URL
            else -> null
        }

        var videoUrl: String? = null
        if (videoSite != null && videoResponse != null) {
            videoUrl = "$videoSite${videoResponse.key}"
        }

        catalogEntity.videoEntity = VideoEntity(videoUrl)


        val resource = Resource.success(
            catalogEntity
        )

        val catalog = MutableLiveData<Resource<CatalogEntity>>()
        catalog.value = resource

        `when`(catalogRepository.getVideo(catalogEntity.catalogId, Constant.MOVIE)).thenReturn(
            catalog
        )

        viewModel.videoUrl.observeForever(catalogObserver)

        verify(catalogObserver).onChanged(resource)
    }

    @Test
    fun getTvShowVideo() {
        val tvShowResponse = apiHelper.getPopularTvShows().first()
        var catalogEntity: CatalogEntity
        with(tvShowResponse) {
            catalogEntity = CatalogEntity(
                id, Constant.TV_SHOW, null, name, posterPath, overview
            )
        }

        viewModel.setSelectedCatalog(catalogEntity.catalogId, Constant.TV_SHOW)

        val videoResponse =
            apiHelper.getMovieVideos(catalogEntity.catalogId).find { it.type == Constant.TRAILER }
        val videoSite = when (videoResponse?.site) {
            Constant.SITE_YOUTUBE -> Constant.YOUTUBE_VIDEO_URL
            Constant.SITE_VIMEO -> Constant.VIMEO_VIDEO_URL
            else -> null
        }

        var videoUrl: String? = null
        if (videoSite != null && videoResponse != null) {
            videoUrl = "$videoSite${videoResponse.key}"
        }

        catalogEntity.videoEntity = VideoEntity(videoUrl)


        val resource = Resource.success(
            catalogEntity
        )

        val catalog = MutableLiveData<Resource<CatalogEntity>>()
        catalog.value = resource

        `when`(catalogRepository.getVideo(catalogEntity.catalogId, Constant.TV_SHOW)).thenReturn(
            catalog
        )

        viewModel.videoUrl.observeForever(catalogObserver)

        verify(catalogObserver).onChanged(resource)
    }

    @Test
    fun setFavorite() {
        val catalogDao = mock(CatalogDao::class.java)
        val localData = LocalDataSource.getInstance(catalogDao)

        val movieResponse = apiHelper.getPopularMovies().first()
        var movieCatalog: CatalogEntity
        with(movieResponse) {
            movieCatalog = CatalogEntity(
                id, Constant.MOVIE, null, title, posterPath, overview, false
            )
        }

        viewModel.setSelectedCatalog(movieCatalog.catalogId, Constant.MOVIE)

        val expectedMovieCatalog = movieCatalog.copy(isFavorite = true)
        doNothing().`when`(catalogDao).updateCatalog(expectedMovieCatalog)

        localData.setCatalogFavorite(movieCatalog, true)

        verify(catalogDao, times(1)).updateCatalog(expectedMovieCatalog)

        val tvShowResponse = apiHelper.getPopularTvShows().first()
        var tvShowCatalog: CatalogEntity
        with(tvShowResponse) {
            tvShowCatalog = CatalogEntity(
                id, Constant.TV_SHOW, null, name, posterPath, overview, false
            )
        }

        viewModel.setSelectedCatalog(tvShowCatalog.catalogId, Constant.TV_SHOW)

        val expectedTvShowCatalog = tvShowCatalog.copy(isFavorite = true)
        doNothing().`when`(catalogDao).updateCatalog(expectedTvShowCatalog)

        localData.setCatalogFavorite(tvShowCatalog, true)

        verify(catalogDao, times(1)).updateCatalog(expectedTvShowCatalog)
    }
}