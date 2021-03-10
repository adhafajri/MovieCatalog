package com.adhafajri.moviecatalog.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.vo.Resource
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

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<CatalogEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<CatalogEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getPopularMovies() {
        val dummyCourses = Resource.success(pagedList)
        `when`(dummyCourses.data?.size).thenReturn(5)

        val catalog = MutableLiveData<Resource<PagedList<CatalogEntity>>>()
        catalog.value = dummyCourses

        `when`(catalogRepository.getPopularMovies()).thenReturn(catalog)
        val catalogEntities = viewModel.getPopularMovies().value?.data
        verify(catalogRepository).getPopularMovies()
        assertNotNull(catalogEntities)
        assertEquals(catalog.value?.data?.size, catalogEntities?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }

    @Test
    fun getUpcomingMovies() {
        val dummyCourses = Resource.success(pagedList)
        `when`(dummyCourses.data?.size).thenReturn(5)

        val catalog = MutableLiveData<Resource<PagedList<CatalogEntity>>>()
        catalog.value = dummyCourses

        `when`(catalogRepository.getUpcomingMovies()).thenReturn(catalog)
        val catalogEntities = viewModel.getUpcomingMovies().value?.data
        verify(catalogRepository).getUpcomingMovies()
        assertNotNull(catalogEntities)
        assertEquals(catalog.value?.data?.size, catalogEntities?.size)

        viewModel.getUpcomingMovies().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}