package com.adhafajri.moviecatalog.ui.show

import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data

class ShowViewModel : ViewModel() {
    fun getShowCatalogs(): List<CatalogEntity> = Data.generateCatalogs(Constant.SHOWS)
}