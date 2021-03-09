package com.adhafajri.moviecatalog.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIHelper
import com.adhafajri.moviecatalog.utils.api.APIInterface
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private lateinit var apiHelper: APIHelper

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<CatalogEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
        apiHelper = APIHelper(APIClient().getClient().create(
            APIInterface::class.java))
    }

    @Test
    fun getPopularMovies() {
        val movies = apiHelper.getPopularMovies()
        val movieList = ArrayList<CatalogEntity>()
        val catalogList = MutableLiveData<List<CatalogEntity>>()
        movies?.forEach {
            with(it) {
                val catalog = CatalogEntity(
                    id,
                    Constant.MOVIE,
                    title,
                    "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                    overview
                )
                movieList.add(catalog)
            }
        }
        catalogList.value = movieList

        `when`(catalogRepository.getPopularMovies()).thenReturn(catalogList)
        val catalogEntities = viewModel.getPopularMovies().value
        verify(catalogRepository).getPopularMovies()
        assertNotNull(catalogEntities)
        assertEquals(catalogList.value?.size, catalogEntities?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(movieList)
    }

    @Test
    fun getUpcomingMovies() {
        val movies = apiHelper.getUpcomingMovies()
        val movieList = ArrayList<CatalogEntity>()
        val catalogList = MutableLiveData<List<CatalogEntity>>()
        movies?.forEach {
            with(it) {
                val catalog = CatalogEntity(
                    id,
                    Constant.MOVIE,
                    title,
                    "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                    overview
                )
                movieList.add(catalog)
            }
        }
        catalogList.value = movieList

        `when`(catalogRepository.getUpcomingMovies()).thenReturn(catalogList)
        val catalogEntities = viewModel.getUpcomingMovies().value
        verify(catalogRepository).getUpcomingMovies()
        assertNotNull(catalogEntities)
        assertEquals(catalogList.value?.size, catalogEntities?.size)

        viewModel.getUpcomingMovies().observeForever(observer)
        verify(observer).onChanged(movieList)
    }
}