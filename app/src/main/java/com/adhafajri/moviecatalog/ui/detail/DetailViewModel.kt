package com.adhafajri.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.data.PersonEntity
import com.adhafajri.moviecatalog.utils.Data

class DetailViewModel : ViewModel() {
    private lateinit var catalogId: String

    fun setSelectedCatalog(catalogId: String) {
        this.catalogId = catalogId
    }

    fun getCatalog(): CatalogEntity? {
        var catalog: CatalogEntity? = null
        for (catalogEntity in Data.generateCatalogs()) {
            if (catalogEntity.catalogId == catalogId) {
                catalog = catalogEntity
            }
        }
        return catalog
    }

    fun getPersons(): List<PersonEntity> {
        val persons = ArrayList<PersonEntity>()
        for (person in Data.generatePersons()) {
            if (person.catalogId == catalogId) {
                persons.add(person)
            }
        }

        return persons
    }
}