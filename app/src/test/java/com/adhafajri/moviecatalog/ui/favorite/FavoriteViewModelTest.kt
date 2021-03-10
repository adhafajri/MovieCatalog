package com.adhafajri.moviecatalog.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
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
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<CatalogEntity>>

    @Mock
    private lateinit var pagedList: PagedList<CatalogEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogRepository)
    }

    @Test
    fun getFavorites() {
        val dummyCourses = pagedList
        Mockito.`when`(dummyCourses.size).thenReturn(5)

        val catalog = MutableLiveData<PagedList<CatalogEntity>>()
        catalog.value = dummyCourses

        Mockito.`when`(catalogRepository.getFavoriteCatalogs()).thenReturn(catalog)
        val catalogEntities = viewModel.getFavorites().value
        verify(catalogRepository).getFavoriteCatalogs()
        Assert.assertNotNull(catalogEntities)
        Assert.assertEquals(catalog.value?.size, catalogEntities?.size)

        viewModel.getFavorites().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}