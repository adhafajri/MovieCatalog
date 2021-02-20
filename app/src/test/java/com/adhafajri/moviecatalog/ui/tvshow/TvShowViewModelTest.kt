package com.adhafajri.moviecatalog.ui.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModelTv: TvShowViewModel

    @Before
    fun setUp() {
        viewModelTv = TvShowViewModel()
    }

    @Test
    fun getShowCatalogs() {
        val showCatalogEntities = viewModelTv.getTvShowCatalogs()
        assertNotNull(showCatalogEntities)
        assertEquals(5, showCatalogEntities.size)
    }
}