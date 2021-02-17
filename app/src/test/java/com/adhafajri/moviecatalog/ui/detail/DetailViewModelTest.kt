package com.adhafajri.moviecatalog.ui.detail

import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovieCatalog = Data.generateCatalogs(Constant.MOVIES)[0]
    private val movieCatalogId = dummyMovieCatalog.catalogId

    private val dummyShowCatalog = Data.generateCatalogs(Constant.SHOWS)[0]
    private val showCatalogId = dummyShowCatalog.catalogId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getMovieCatalog() {
        viewModel.setSelectedCatalog(movieCatalogId, Constant.MOVIES)
        val movieCatalogEntity = viewModel.getCatalog()
        assertNotNull(movieCatalogEntity)
        assertEquals(dummyMovieCatalog.catalogId, movieCatalogEntity?.catalogId)
        assertEquals(dummyMovieCatalog.title, movieCatalogEntity?.title)
        assertEquals(dummyMovieCatalog.year, movieCatalogEntity?.year)
        assertEquals(dummyMovieCatalog.posterPath, movieCatalogEntity?.posterPath)
        assertEquals(dummyMovieCatalog.trailerPath, movieCatalogEntity?.trailerPath)
        assertEquals(dummyMovieCatalog.synopsis, movieCatalogEntity?.synopsis)
        assertEquals(dummyMovieCatalog.directors, movieCatalogEntity?.directors)
        assertEquals(dummyMovieCatalog.writers, movieCatalogEntity?.writers)
        assertEquals(dummyMovieCatalog.stars, movieCatalogEntity?.stars)
    }

    @Test
    fun getShowCatalog() {
        viewModel.setSelectedCatalog(showCatalogId, Constant.SHOWS)
        val showCatalogEntity = viewModel.getCatalog()
        assertNotNull(showCatalogEntity)
        assertEquals(dummyShowCatalog.catalogId, showCatalogEntity?.catalogId)
        assertEquals(dummyShowCatalog.title, showCatalogEntity?.title)
        assertEquals(dummyShowCatalog.year, showCatalogEntity?.year)
        assertEquals(dummyShowCatalog.posterPath, showCatalogEntity?.posterPath)
        assertEquals(dummyShowCatalog.trailerPath, showCatalogEntity?.trailerPath)
        assertEquals(dummyShowCatalog.synopsis, showCatalogEntity?.synopsis)
        assertEquals(dummyShowCatalog.directors, showCatalogEntity?.directors)
        assertEquals(dummyShowCatalog.writers, showCatalogEntity?.writers)
        assertEquals(dummyShowCatalog.stars, showCatalogEntity?.stars)
    }
}