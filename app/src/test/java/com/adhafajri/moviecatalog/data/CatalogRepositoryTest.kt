package com.adhafajri.moviecatalog.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adhafajri.moviecatalog.data.source.remote.RemoteDataSource
import com.adhafajri.moviecatalog.utils.Data
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val catalogRepository = TestCatalogRepository(remote)

    private val catalogResponses = Data.generateCatalogs()
    private val catalogId = catalogResponses.first().catalogId

    private val creditResponses = Data.generateCredits()
    private val creditId = creditResponses.first().creditId

    private val personResponses = Data.generatePersons()
    private val personId = personResponses.first().personId

    private val personJobResponses = Data.generatePersonJobs()
    private val personJobId = personJobResponses.first().personId

    private val videoResponses = Data.generateVideos()
    private val videoId = videoResponses.first().videoId

    @Test
    fun getPopularMovies() {
        doAnswer {
            (it.arguments.first())
        }.`when`(remote).getMovieVideos(any())
        }
    }

    @Test
    fun getUpcomingMovies() {
    }

    @Test
    fun getMovieDetails() {
    }

    @Test
    fun getMovieCredits() {
    }

    @Test
    fun getMovieVideo() {
    }

    @Test
    fun getPopularTvShows() {
    }

    @Test
    fun getTodayAiringTvShows() {
    }

    @Test
    fun getTvShowDetails() {
    }

    @Test
    fun getTvShowCredits() {
    }

    @Test
    fun getTvShowVideo() {
    }
}