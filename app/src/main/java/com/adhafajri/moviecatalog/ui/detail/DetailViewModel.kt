package com.adhafajri.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.data.PersonEntity
import com.adhafajri.moviecatalog.data.PersonTitleEntity
import com.adhafajri.moviecatalog.data.TitleEntity
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
                val personTitles = getPersonTitles(person.personId)
                val titles = ArrayList<TitleEntity>()
                for (personTitle in personTitles) {
                    val title = getTitle(personTitle.titleId)
                    if (title != null) {
                        titles.add(title)
                    }
                }
                person.titles = titles
                persons.add(person)
            }
        }

        return persons
    }

    private fun getPersonTitles(personId: String): List<PersonTitleEntity> {
        val personTitles = ArrayList<PersonTitleEntity>()
        for (personTitle in Data.generatePersonTitles()) {
            if (personTitle.personId == personId) {
                personTitles.add(personTitle)
            }
        }

        return personTitles
    }

    private fun getTitle(titleId: String): TitleEntity? {
        var title: TitleEntity? = null
        for (titleEntity in Data.generateTitles()) {
            if (titleEntity.titleId == titleId) {
                title = titleEntity
            }
        }

        return title
    }
}