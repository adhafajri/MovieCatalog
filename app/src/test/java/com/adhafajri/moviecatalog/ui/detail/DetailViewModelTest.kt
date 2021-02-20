package com.adhafajri.moviecatalog.ui.detail

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
    fun getCatalog() {
        viewModel.setSelectedCatalog(catalogId)
        val catalogEntity = viewModel.getCatalog()
        assertNotNull(catalogEntity)
        assertEquals(dummyCatalog.catalogId, catalogEntity?.catalogId)
        assertEquals(dummyCatalog.title, catalogEntity?.title)
        assertEquals(dummyCatalog.year, catalogEntity?.year)
        assertEquals(dummyCatalog.posterPath, catalogEntity?.posterPath)
        assertEquals(dummyCatalog.trailerPath, catalogEntity?.trailerPath)
        assertEquals(dummyCatalog.overview, catalogEntity?.overview)
    }

    @Test
    fun getPersons() {
        viewModel.setSelectedCatalog(catalogId)
        val personEntities = viewModel.getPersons()
        assertNotNull(personEntities)
        assertEquals(2, personEntities.size)
    }
}