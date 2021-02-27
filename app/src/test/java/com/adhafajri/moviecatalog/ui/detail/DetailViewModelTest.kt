package com.adhafajri.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CreditEntity
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity
import com.adhafajri.moviecatalog.data.source.local.entity.PersonJobEntity
import com.adhafajri.moviecatalog.data.source.local.entity.VideoEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIHelper
import com.adhafajri.moviecatalog.utils.api.APIInterface
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
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
    private lateinit var creditsObserver: Observer<List<CreditEntity>>

    @Mock
    private lateinit var videoObserver: Observer<VideoEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
        apiHelper = APIHelper(APIClient().getClient().create(
            APIInterface::class.java))
    }

    @Test
    fun getMovieCredits() {
        val catalogMovieTest = apiHelper.getPopularMovies()?.first()
        val catalogMovieId = catalogMovieTest?.id
        val creditsTest = catalogMovieId?.let { apiHelper.getMovieCredits(it) }

        catalogMovieId?.let { viewModel.setSelectedCatalog(it, Constant.MOVIE) }

        val credits = MutableLiveData<List<CreditEntity>>()
        val creditList = ArrayList<CreditEntity>()
        val personsJobList = ArrayList<PersonJobEntity>()

        creditsTest?.forEach {
            with(it) {
                val personsJob = PersonJobEntity(
                    id,
                    name,
                    job
                )
                personsJobList.add(personsJob)
            }
        }

        personsJobList.groupBy { it.personId }.entries.map { (id, it) ->
            creditList.add(
                CreditEntity(id, PersonEntity(id, it.first().name), it)
            )
        }
        credits.value = creditList

        `when`(catalogMovieId?.let { catalogRepository.getMovieCredits(it) }).thenReturn(credits)
        val creditEntities = viewModel.getCredits()?.value
        catalogMovieId?.let { verify(catalogRepository).getMovieCredits(it) }
        assertNotNull(creditEntities)
        assertEquals(credits.value?.size, creditEntities?.size)

        viewModel.getCredits()?.observeForever(creditsObserver)
        verify(creditsObserver).onChanged(creditList)
    }

    @Test
    fun getTvShowCredits() {
        val catalogTvShowTest = apiHelper.getPopularTvShows()?.first()
        val catalogTvShowId = catalogTvShowTest?.id
        val creditsTest = catalogTvShowId?.let { apiHelper.getTvShowCredits(it) }

        catalogTvShowId?.let { viewModel.setSelectedCatalog(it, Constant.TV_SHOW) }

        val credits = MutableLiveData<List<CreditEntity>>()
        val creditList = ArrayList<CreditEntity>()
        val personsJobList = ArrayList<PersonJobEntity>()

        creditsTest?.forEach {
            with(it) {
                val personsJob = PersonJobEntity(
                    id,
                    name,
                    job
                )
                personsJobList.add(personsJob)
            }
        }

        personsJobList.groupBy { it.personId }.entries.map { (id, it) ->
            creditList.add(
                CreditEntity(id, PersonEntity(id, it.first().name), it)
            )
        }
        credits.value = creditList

        `when`(catalogTvShowId?.let { catalogRepository.getTvShowCredits(it) }).thenReturn(credits)
        val creditEntities = viewModel.getCredits()?.value
        catalogTvShowId?.let { verify(catalogRepository).getTvShowCredits(it) }
        assertNotNull(creditEntities)
        assertEquals(credits.value?.size, creditEntities?.size)

        viewModel.getCredits()?.observeForever(creditsObserver)
        verify(creditsObserver).onChanged(creditList)
    }

    @Test
    fun getMovieVideo() {
        val catalogMovieTest = apiHelper.getPopularMovies()?.first()
        val catalogMovieId = catalogMovieTest?.id
        val videosTest = catalogMovieId?.let { apiHelper.getMovieVideos(it) }?.first()

        catalogMovieId?.let { viewModel.setSelectedCatalog(it, Constant.MOVIE) }

        val videoLiveData = MutableLiveData<VideoEntity>()
        var video: VideoEntity? = null
        videosTest?.let {
            with(it) {
                video = VideoEntity(
                    id,
                    key,
                    site
                )
            }
        }
        videoLiveData.value = video

        `when`(catalogMovieId?.let { catalogRepository.getMovieVideo(it) }).thenReturn(videoLiveData)
        val videoEntity = viewModel.getVideo()?.value
        catalogMovieId?.let { verify(catalogRepository).getMovieVideo(it) }
        assertNotNull(videoEntity)
        assertEquals(videosTest?.id, videoEntity?.videoId)
        assertEquals(videosTest?.key, videoEntity?.key)
        assertEquals(videosTest?.site, videoEntity?.site)

        viewModel.getVideo()?.observeForever(videoObserver)
        verify(videoObserver).onChanged(video)
    }

    @Test
    fun getTvShowVideo() {
        val catalogTvShowTest = apiHelper.getPopularTvShows()?.first()
        val catalogTvShowId = catalogTvShowTest?.id
        val videosTest = catalogTvShowId?.let { apiHelper.getTvShowsVideos(it) }?.first()

        catalogTvShowId?.let { viewModel.setSelectedCatalog(it, Constant.TV_SHOW) }

        val videoLiveData = MutableLiveData<VideoEntity>()
        var video: VideoEntity? = null
        videosTest?.let {
            with(it) {
                video = VideoEntity(
                    id,
                    key,
                    site
                )
            }
        }
        videoLiveData.value = video

        `when`(catalogTvShowId?.let { catalogRepository.getTvShowVideo(it) }).thenReturn(
            videoLiveData)
        val videoEntity = viewModel.getVideo()?.value
        catalogTvShowId?.let { verify(catalogRepository).getTvShowVideo(it) }
        assertNotNull(videoEntity)
        assertEquals(videosTest?.id, videoEntity?.videoId)
        assertEquals(videosTest?.key, videoEntity?.key)
        assertEquals(videosTest?.site, videoEntity?.site)

        viewModel.getVideo()?.observeForever(videoObserver)
        verify(videoObserver).onChanged(video)
    }
}