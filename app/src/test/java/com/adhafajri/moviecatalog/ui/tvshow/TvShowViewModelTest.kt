package com.adhafajri.moviecatalog.ui.tvshow

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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    private lateinit var apiHelper: APIHelper

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<CatalogEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
        apiHelper = APIHelper(APIClient().getClient().create(
            APIInterface::class.java))
    }

    @Test
    fun getPopularTvShows() {
        val tvShows = apiHelper.getPopularTvShows()
        val tvShowList = ArrayList<CatalogEntity>()
        val catalogList = MutableLiveData<List<CatalogEntity>>()
        tvShows?.forEach {
            with(it) {
                val catalog = CatalogEntity(
                    id,
                    Constant.TV_SHOW,
                    name,
                    "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                    overview
                )
                tvShowList.add(catalog)
            }
        }
        catalogList.value = tvShowList

        Mockito.`when`(catalogRepository.getPopularTvShows()).thenReturn(catalogList)
        val catalogEntities = viewModel.getPopularTvShows().value
        verify(catalogRepository).getPopularTvShows()
        assertNotNull(catalogEntities)
        assertEquals(catalogList.value?.size, catalogEntities?.size)

        viewModel.getPopularTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowList)
    }

    @Test
    fun getTodayAiringTvShows() {
        val tvShows = apiHelper.getTodayAiringTvShows()
        val tvShowList = ArrayList<CatalogEntity>()
        val catalogList = MutableLiveData<List<CatalogEntity>>()
        tvShows?.forEach {
            with(it) {
                val catalog = CatalogEntity(
                    id,
                    Constant.TV_SHOW,
                    name,
                    "${Constant.THE_MOVIE_DB_IMG_URL}${posterPath}",
                    overview
                )
                tvShowList.add(catalog)
            }
        }
        catalogList.value = tvShowList

        Mockito.`when`(catalogRepository.getTodayAiringTvShows()).thenReturn(catalogList)
        val catalogEntities = viewModel.getTodayAiringTvShows().value
        verify(catalogRepository).getTodayAiringTvShows()
        assertNotNull(catalogEntities)
        assertEquals(catalogList.value?.size, catalogEntities?.size)

        viewModel.getTodayAiringTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowList)
    }
}