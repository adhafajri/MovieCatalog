package com.adhafajri.moviecatalog.ui.movie

import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data

class MovieViewModel : ViewModel() {
    fun getMovieCatalogs(): List<CatalogEntity> = Data.generateCatalogs(Constant.MOVIES)
}