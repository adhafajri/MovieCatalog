package com.adhafajri.moviecatalog.ui.tvshow

import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data

class TvShowViewModel : ViewModel() {
    fun getTvShowCatalogs(): List<CatalogEntity> {
        val catalogs = ArrayList<CatalogEntity>()
        for (catalogEntity in Data.generateCatalogs()) {
            if (catalogEntity.type == Constant.TV_SHOW) {
                catalogs.add(catalogEntity)
            }
        }
        return catalogs
    }
}