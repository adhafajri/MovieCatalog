package com.adhafajri.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.adhafajri.moviecatalog.data.CatalogRepository
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogWithPerson
import com.adhafajri.moviecatalog.data.source.local.entity.PersonWithJob
import com.adhafajri.moviecatalog.data.source.local.entity.VideoEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.dicoding.academies.vo.Resource

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    private var catalogId =  MutableLiveData<String>()
    private lateinit var type: String

    fun setSelectedCatalog(catalogId: String, type: String) {
        this.catalogId.value = catalogId
        this.type = type
    }

    var catalogPerson: LiveData<Resource<CatalogWithPerson>> =
        Transformations.switchMap(catalogId) {
            catalogRepository.getCatalogWithPersons(it, type)
        }

    var videoUrl: LiveData<Resource<CatalogEntity>> =
        Transformations.switchMap(catalogId) {
            catalogRepository.getVideo(it, type)
        }

    fun getPersonWithJobs(personId: String): LiveData<Resource<PersonWithJob>> =
        Transformations.switchMap(catalogId) {
            catalogRepository.getPersonWithJobs(it, personId, type)
        }

//    fun getCredits(): LiveData<List<CreditEntity>>? {
//        return when (type) {
//            Constant.MOVIE -> catalogRepository.getCatalogWithPersons(catalogId)
//            Constant.TV_SHOW -> catalogRepository.getCatalogWithPersons(catalogId)
//            else -> null
//        }
//    }

//    fun getVideo(): LiveData<VideoEntity>? {
//        return when (type) {
//            Constant.MOVIE -> catalogRepository.getMovieVideo(catalogId)
//            Constant.TV_SHOW -> catalogRepository.getTvShowVideo(catalogId)
//            else -> null
//        }
//    }
}