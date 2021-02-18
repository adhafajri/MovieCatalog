package com.adhafajri.moviecatalog.ui.detail

import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyCatalog = Data.generateCatalogs()[0]
    private val catalogId = dummyCatalog.catalogId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getMovieCatalog() {
        viewModel.setSelectedCatalog(catalogId)
        val movieCatalogEntity = viewModel.getCatalog()
        assertNotNull(movieCatalogEntity)
        assertEquals(dummyCatalog.catalogId, movieCatalogEntity?.catalogId)
        assertEquals(dummyCatalog.title, movieCatalogEntity?.title)
        assertEquals(dummyCatalog.year, movieCatalogEntity?.year)
        assertEquals(dummyCatalog.posterPath, movieCatalogEntity?.posterPath)
        assertEquals(dummyCatalog.trailerPath, movieCatalogEntity?.trailerPath)
        assertEquals(dummyCatalog.overview, movieCatalogEntity?.overview)
    }
}