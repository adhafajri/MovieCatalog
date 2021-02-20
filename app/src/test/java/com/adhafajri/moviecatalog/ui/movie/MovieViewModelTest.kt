package com.adhafajri.moviecatalog.ui.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovieCatalogs() {
        val movieCatalogEntities = viewModel.getMovieCatalogs()
        assertNotNull(movieCatalogEntities)
        assertEquals(5, movieCatalogEntities.size)
    }
}