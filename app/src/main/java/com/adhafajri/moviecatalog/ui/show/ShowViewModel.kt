package com.adhafajri.moviecatalog.ui.show

import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.data.PersonEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data

class ShowViewModel : ViewModel() {
    fun getShowCatalogs(): List<CatalogEntity> {
        val catalogs = ArrayList<CatalogEntity>()
        for (catalogEntity in Data.generateCatalogs()) {
            if (catalogEntity.type == Constant.TV_SHOW) {
                catalogs.add(catalogEntity)
            }
        }
        return catalogs
    }
}