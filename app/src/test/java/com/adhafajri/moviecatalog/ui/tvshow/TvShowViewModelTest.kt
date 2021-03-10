package com.adhafajri.moviecatalog.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

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
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun getPopularTvShows() {
        val dummyCourses = Resource.success(pagedList)
        Mockito.`when`(dummyCourses.data?.size).thenReturn(5)

        val catalog = MutableLiveData<Resource<PagedList<CatalogEntity>>>()
        catalog.value = dummyCourses

        Mockito.`when`(catalogRepository.getPopularTvShows()).thenReturn(catalog)
        val catalogEntities = viewModel.getPopularTvShows().value?.data
        verify(catalogRepository).getPopularTvShows()
        Assert.assertNotNull(catalogEntities)
        Assert.assertEquals(catalog.value?.data?.size, catalogEntities?.size)

        viewModel.getPopularTvShows().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }

    @Test
    fun getTodayAiringTvShows() {
        val dummyCourses = Resource.success(pagedList)
        Mockito.`when`(dummyCourses.data?.size).thenReturn(5)

        val catalog = MutableLiveData<Resource<PagedList<CatalogEntity>>>()
        catalog.value = dummyCourses

        Mockito.`when`(catalogRepository.getTodayAiringTvShows()).thenReturn(catalog)
        val catalogEntities = viewModel.getTodayAiringTvShows().value?.data
        verify(catalogRepository).getTodayAiringTvShows()
        Assert.assertNotNull(catalogEntities)
        Assert.assertEquals(catalog.value?.data?.size, catalogEntities?.size)

        viewModel.getTodayAiringTvShows().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}