package com.adhafajri.moviecatalog.ui.show

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

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