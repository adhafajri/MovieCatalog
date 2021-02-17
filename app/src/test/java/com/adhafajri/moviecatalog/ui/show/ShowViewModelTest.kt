package com.adhafajri.moviecatalog.ui.show

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ShowViewModelTest {

    private lateinit var viewModel: ShowViewModel

    @Before
    fun setUp() {
        viewModel = ShowViewModel()
    }

    @Test
    fun getShowCatalogs() {
        val showCatalogEntities = viewModel.getShowCatalogs()
        assertNotNull(showCatalogEntities)
        assertEquals(5, showCatalogEntities.size)
    }
}