package com.adhafajri.moviecatalog.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIHelper
import com.adhafajri.moviecatalog.utils.api.APIInterface
import com.dicoding.academies.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val apiHelper = APIHelper(APIClient().getClient().create(
        APIInterface::class.java))


    private val remote = mock(RemoteDataSource::class.java)
    private val catalogRepository = TestCatalogRepository(remote)

    private val popularMovieResponse = apiHelper.getPopularMovies()
    private val popularMovieId = popularMovieResponse?.first()?.id

    private val upcomingMovieResponse = apiHelper.getUpcomingMovies()
    private val upcomingMovieId = upcomingMovieResponse?.first()?.id

    private val popularTvShowResponse = apiHelper.getPopularTvShows()
    private val popularTvShowId = popularTvShowResponse?.first()?.id

    private val todayAiringTvShowResponse = apiHelper.getTodayAiringTvShows()
    private val todayAiringTvShowId = todayAiringTvShowResponse?.first()?.id

    private val popularMovieVideosResponse = popularMovieId?.let { apiHelper.getMovieVideos(it) }

    private val upcomingMovieVideosResponse = upcomingMovieId?.let { apiHelper.getMovieVideos(it) }

    private val popularTvShowVideosResponse =
        popularTvShowId?.let { apiHelper.getTvShowsVideos(it) }

    private val todayAiringTvShowVideosResponse =
        todayAiringTvShowId?.let { apiHelper.getTvShowsVideos(it) }

    private val popularMovieCreditResponse = popularMovieId?.let { apiHelper.getMovieCredits(it) }

    private val upcomingMovieCreditResponse = upcomingMovieId?.let { apiHelper.getMovieCredits(it) }

    private val popularTvShowCreditResponse =
        popularTvShowId?.let { apiHelper.getTvShowCredits(it) }

    private val todayAiringTvShowCreditResponse =
        todayAiringTvShowId?.let { apiHelper.getTvShowCredits(it) }

    @Test
    fun getPopularMovies() {
        doAnswer {
            (it.arguments.first() as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(popularMovieResponse)
        }.`when`(remote).getPopularMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getPopularMovies())
        verify(remote).getPopularMovies(any())
        assertNotNull(movieEntities)
        assertEquals(popularMovieResponse?.size?.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getUpcomingMovies() {
        doAnswer {
            (it.arguments.first() as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(upcomingMovieResponse)
        }.`when`(remote).getUpcomingMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getUpcomingMovies())
        verify(remote).getUpcomingMovies(any())
        assertNotNull(movieEntities)
        assertEquals(upcomingMovieResponse?.size?.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getPopularTvShows() {
        doAnswer {
            (it.arguments.first() as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(popularTvShowResponse)
        }.`when`(remote).getPopularTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(catalogRepository.getPopularTvShows())
        verify(remote).getPopularTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(popularTvShowResponse?.size?.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getTodayAiringTvShows() {
        doAnswer {
            (it.arguments.first() as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(todayAiringTvShowResponse)
        }.`when`(remote).getTodayAiringTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(catalogRepository.getTodayAiringTvShows())
        verify(remote).getTodayAiringTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(todayAiringTvShowResponse?.size?.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getPopularMovieVideo() {
        popularMovieId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadVideosCallback)
                        .onAllVideosReceived(popularMovieVideosResponse)
                }.`when`(remote).getMovieVideos(eq(id), any())

                val movieVideoEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getMovieVideo(id))
                verify(remote).getMovieVideos(id, any())

                assertNotNull(movieVideoEntities)
                assertNotNull(movieVideoEntities.videoId)
                assertNotNull(movieVideoEntities.key)
                assertNotNull(movieVideoEntities.site)
                assertEquals(popularMovieVideosResponse?.first(), movieVideoEntities)
            }
        }
    }

    @Test
    fun getUpcomingMovieVideo() {
        popularMovieId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadVideosCallback)
                        .onAllVideosReceived(upcomingMovieVideosResponse)
                }.`when`(remote).getMovieVideos(eq(id), any())

                val movieVideoEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getMovieVideo(id))
                verify(remote).getMovieVideos(id, any())

                assertNotNull(movieVideoEntities)
                assertNotNull(movieVideoEntities.videoId)
                assertNotNull(movieVideoEntities.key)
                assertNotNull(movieVideoEntities.site)
                assertEquals(upcomingMovieVideosResponse?.first(), movieVideoEntities)
            }
        }
    }

    @Test
    fun getPopularTvShowVideo() {
        popularTvShowId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadVideosCallback)
                        .onAllVideosReceived(popularTvShowVideosResponse)
                }.`when`(remote).getTvShowVideos(eq(id), any())

                val tvShowVideoEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getTvShowVideo(id))
                verify(remote).getTvShowVideos(id, any())

                assertNotNull(tvShowVideoEntities)
                assertNotNull(tvShowVideoEntities.videoId)
                assertNotNull(tvShowVideoEntities.key)
                assertNotNull(tvShowVideoEntities.site)
                assertEquals(popularTvShowVideosResponse?.first(), tvShowVideoEntities)
            }
        }
    }

    @Test
    fun getTodayAiringTvShowVideo() {
        todayAiringTvShowId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadVideosCallback)
                        .onAllVideosReceived(todayAiringTvShowVideosResponse)
                }.`when`(remote).getTvShowVideos(eq(id), any())

                val tvShowVideoEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getTvShowVideo(id))
                verify(remote).getTvShowVideos(id, any())

                assertNotNull(tvShowVideoEntities)
                assertNotNull(tvShowVideoEntities.videoId)
                assertNotNull(tvShowVideoEntities.key)
                assertNotNull(tvShowVideoEntities.site)
                assertEquals(todayAiringTvShowVideosResponse?.first(), tvShowVideoEntities)
            }
        }
    }

    @Test
    fun getPopularMovieCredits() {
        popularMovieId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadCreditsCallback)
                        .onAllCreditsReceived(popularMovieCreditResponse)
                }.`when`(remote).getMovieCredits(eq(id), any())
                val movieCreditEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getMovieCredits(id))
                verify(remote).getTodayAiringTvShows(any())
                assertNotNull(movieCreditEntities)
                assertEquals(popularMovieCreditResponse?.size?.toLong(),
                    movieCreditEntities.size.toLong())
            }
        }
    }

    @Test
    fun getUpcomingMovieCredits() {
        upcomingMovieId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadCreditsCallback)
                        .onAllCreditsReceived(upcomingMovieCreditResponse)
                }.`when`(remote).getMovieCredits(eq(id), any())
                val movieCreditEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getMovieCredits(id))
                verify(remote).getTodayAiringTvShows(any())
                assertNotNull(movieCreditEntities)
                assertEquals(upcomingMovieCreditResponse?.size?.toLong(),
                    movieCreditEntities.size.toLong())
            }
        }
    }

    @Test
    fun getPopularTvShowCredits() {
        popularTvShowId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadCreditsCallback)
                        .onAllCreditsReceived(popularTvShowCreditResponse)
                }.`when`(remote).getMovieCredits(eq(id), any())
                val tvShowCreditEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getTvShowCredits(id))
                verify(remote).getTodayAiringTvShows(any())
                assertNotNull(tvShowCreditEntities)
                assertEquals(popularTvShowCreditResponse?.size?.toLong(),
                    tvShowCreditEntities.size.toLong())
            }
        }
    }

    @Test
    fun getTodayAiringTvShowCredits() {
        todayAiringTvShowId?.let { id ->
            {
                doAnswer {
                    (it.arguments.first() as RemoteDataSource.LoadCreditsCallback)
                        .onAllCreditsReceived(todayAiringTvShowCreditResponse)
                }.`when`(remote).getMovieCredits(eq(id), any())
                val tvShowCreditEntities =
                    LiveDataTestUtil.getValue(catalogRepository.getTvShowCredits(id))
                verify(remote).getTodayAiringTvShows(any())
                assertNotNull(tvShowCreditEntities)
                assertEquals(todayAiringTvShowCreditResponse?.size?.toLong(),
                    tvShowCreditEntities.size.toLong())
            }
        }
    }
}