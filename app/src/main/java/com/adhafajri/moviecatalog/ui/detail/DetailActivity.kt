package com.adhafajri.moviecatalog.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity
import com.adhafajri.moviecatalog.data.source.local.entity.PersonWithJob
import com.adhafajri.moviecatalog.databinding.ActivityDetailBinding
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.vo.Status

class DetailActivity : AppCompatActivity() {
//    private lateinit var detailContentBinding: ContentDetailBinding

//    private lateinit var catalog: CatalogEntity

    private var activityDetailBinding: ActivityDetailBinding? = null

    private val mainBinding get() = activityDetailBinding
    private val contentBinding get() = activityDetailBinding?.detailContent

    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        setSupportActionBar(mainBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogId = extras.getString(Constant.EXTRA_CATALOG_ID)
            val type = extras.getString(Constant.EXTRA_TYPE)
//        val title = extras?.getString(Constant.EXTRA_TITLE)
//        val posterPath = extras?.getString(Constant.EXTRA_POSTER_PATH)
//        val overview = extras?.getString(Constant.EXTRA_OVERVIEW)

            if (catalogId != null && type != null) {
                viewModel.setSelectedCatalog(catalogId, type)
                loadDetails(viewModel)
            }

//        if (catalogId != null && type != null && title != null && posterPath != null && overview != null) {
////            catalog = CatalogEntity(
////                catalogId,
////                type,
////                null
////                title,
////                posterPath,
////                overview,
////            )
//        }

//        viewModel.setSelectedCatalog(catalog.catalogId, catalog.type)

        }
    }

    private fun loadDetails(viewModel: DetailViewModel) {
        val catalogPerson = viewModel.catalogPerson
        catalogPerson.observe(this, Observer { catalogWithPersonResource ->
            if (catalogWithPersonResource != null) {
                when (catalogWithPersonResource.status) {
                    Status.LOADING -> contentBinding?.pbDetails?.visibility = View.VISIBLE
                    Status.SUCCESS -> if (catalogWithPersonResource.data != null) {
                        contentBinding?.pbDetails?.visibility = View.GONE
                        val catalog = catalogWithPersonResource.data.catalog
                        loadCatalogData(catalog)

                        val persons = catalogWithPersonResource.data.persons
                        loadPersonData(persons)
                    }
                    Status.ERROR -> {
                        contentBinding?.pbDetails?.visibility = View.GONE
                        Toast.makeText(
                            applicationContext,
                            "Failed to get details",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        val catalogVideoUrl = viewModel.videoUrl
        catalogVideoUrl.observe(this, Observer { catalogVideoUrlResource ->
            if (catalogVideoUrlResource?.data != null) {
                contentBinding?.btnTrailer?.visibility = View.VISIBLE
                val videoUrl = catalogVideoUrlResource.data.videoEntity?.videoUrl

                contentBinding?.btnTrailer?.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                    startActivity(intent)
                }
            }
        })
//        loadCatalogData(it)

//        viewModel.getCredits()?.observe(this, {
//            loadPersonData(it)
//            detailContentBinding.pbDetails.visibility = View.GONE
//        })

//        viewModel.getVideo()?.observe(this, {
//            with(it) {
//                val link = when (site) {
//                    Constant.SITE_YOUTUBE -> "${Constant.YOUTUBE_VIDEO_URL}$key"
//                    Constant.SITE_VIMEO -> "${Constant.VIMEO_VIDEO_URL}$key"
//                    else -> null
//                }
//
//                if (link != null) {
//                    detailContentBinding.btnTrailer.setOnClickListener {
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
//                        startActivity(intent)
//                    }
//                }
//            }
//        })
    }

    private fun loadPersonData(persons: List<PersonEntity>) {
        val detailAdapter = DetailPersonAdapter()
        val personWithJobs = ArrayList<PersonWithJob>()
        persons.forEach {
            with(it) {
                viewModel.getPersonWithJobs(personId)
                    .observe(this@DetailActivity, Observer { personWithJobResource ->
                        if (personWithJobResource != null) {
                            when (personWithJobResource.status) {
                                Status.SUCCESS -> if (personWithJobResource.data != null) {
                                    personWithJobs.add(personWithJobResource.data)
                                }
                                else -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "Failed to get details",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })
            }
        }

        detailAdapter.setPersons(personWithJobs)
        detailAdapter.notifyDataSetChanged()
        with(contentBinding?.rvPerson) {
            this?.isNestedScrollingEnabled = false
            this?.layoutManager = GridLayoutManager(this?.context, Constant.GRID_SPAN_COUNT)
            this?.setHasFixedSize(true)
            this?.adapter = detailAdapter
        }
    }

    private fun loadCatalogData(catalog: CatalogEntity) {
        supportActionBar?.title = catalog.title

        contentBinding?.tvCatalogTitle?.text = catalog.title

        if (TextUtils.isEmpty(catalog.overview)) {
            contentBinding?.tvOverview?.visibility = View.GONE
            contentBinding?.tvOverviewText?.visibility = View.GONE
        } else {
            contentBinding?.tvOverviewText?.text = catalog.overview
        }

        contentBinding?.imgPoster?.let {
            Glide.with(this)
                .load(catalog.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(it)
        }
    }
}