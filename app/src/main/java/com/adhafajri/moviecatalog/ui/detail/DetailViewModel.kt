package com.adhafajri.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.utils.Data

class DetailViewModel : ViewModel() {
    private lateinit var catalogId: String
    private lateinit var type: String

    fun setSelectedCatalog(catalogId: String, type: String) {
        this.catalogId = catalogId
        this.type = type
    }

    fun getCatalog(): CatalogEntity? {
        var catalog: CatalogEntity? = null
        for (catalogEntity in Data.generateCatalogs(type)) {
            if (catalogEntity.catalogId == catalogId) {
                catalog = catalogEntity
            }
        }
        return catalog
    }
}