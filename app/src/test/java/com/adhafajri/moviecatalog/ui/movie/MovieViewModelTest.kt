package com.adhafajri.moviecatalog.ui.movie

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

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