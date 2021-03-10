package com.adhafajri.moviecatalog.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity
import com.adhafajri.moviecatalog.databinding.ActivityDetailBinding
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.adhafajri.moviecatalog.vo.Status

class DetailActivity : AppCompatActivity() {
    private var activityDetailBinding: ActivityDetailBinding? = null

    private val mainBinding get() = activityDetailBinding
    private val contentBinding get() = activityDetailBinding?.detailContent

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        setSupportActionBar(mainBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogId = extras.getString(Constant.EXTRA_CATALOG_ID)
            val type = extras.getString(Constant.EXTRA_TYPE)

            if (catalogId != null && type != null) {
                viewModel.setSelectedCatalog(catalogId, type)
                loadDetails()
            }


        }
    }

    private fun loadDetails() {
        val catalogPerson = viewModel.catalogPerson
        catalogPerson.observe(this, Observer { catalogWithPersonResource ->
            if (catalogWithPersonResource != null) {
                when (catalogWithPersonResource.status) {
                    Status.LOADING -> contentBinding?.pbDetails?.visibility = View.VISIBLE
                    Status.SUCCESS -> if (catalogWithPersonResource.data != null) {
                        contentBinding?.pbDetails?.visibility = View.GONE
                        val catalog = catalogWithPersonResource.data.catalog
                        loadCatalogData(catalog)

                        val state = catalog.isFavorite
                        setupFavoriteButton(state)


                        val persons = catalogWithPersonResource.data.persons
                        val personList = ArrayList<PersonEntity>()
                        persons.forEach {
                            with(it) {
                                val person = PersonEntity(
                                    personId,
                                    catalogId,
                                    name
                                )
                                personList.add(person)
                            }

                        }
                        loadPersonData(personList)
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
                when (catalogVideoUrlResource.status) {
                    Status.LOADING -> mainBinding?.fabFavorite?.visibility = View.GONE
                    Status.SUCCESS -> {
                        contentBinding?.btnTrailer?.visibility = View.VISIBLE

                        val videoUrl = catalogVideoUrlResource.data.videoEntity?.videoUrl
                        contentBinding?.btnTrailer?.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                            startActivity(intent)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            applicationContext,
                            "Failed to get trailer",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        })
    }

    private fun setupFavoriteButton(state: Boolean) {
        setFavoriteState(state)

        mainBinding?.fabFavorite?.setOnClickListener {
            viewModel.setFavorite()
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            mainBinding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorited
                )
            )
        } else {
            mainBinding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_outline
                )
            )
        }

    }

    private fun loadPersonData(persons: ArrayList<PersonEntity>) {
        val detailAdapter = DetailPersonAdapter()

        detailAdapter.setPersons(persons)
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