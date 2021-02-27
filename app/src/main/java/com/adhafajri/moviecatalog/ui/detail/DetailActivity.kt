package com.adhafajri.moviecatalog.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.CreditEntity
import com.adhafajri.moviecatalog.databinding.ActivityDetailBinding
import com.adhafajri.moviecatalog.databinding.ContentDetailBinding
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {
    private lateinit var detailContentBinding: ContentDetailBinding

    private lateinit var catalog: CatalogEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]

        val extras = intent.extras
        val catalogId = extras?.getString(Constant.EXTRA_CATALOG_ID)
        val type = extras?.getString(Constant.EXTRA_TYPE)
        val title = extras?.getString(Constant.EXTRA_TITLE)
        val posterPath = extras?.getString(Constant.EXTRA_POSTER_PATH)
        val overview = extras?.getString(Constant.EXTRA_OVERVIEW)

        if (catalogId != null && type != null && title != null && posterPath != null && overview != null) {
            catalog = CatalogEntity(
                catalogId,
                type,
                title,
                posterPath,
                overview
            )
        }

        viewModel.setSelectedCatalog(catalog.catalogId, catalog.type)
        loadCatalogData()
        loadDetails(viewModel)
    }

    private fun loadDetails(viewModel: DetailViewModel) {
        detailContentBinding.pbDetails.visibility = View.VISIBLE

        viewModel.getCredits()?.observe(this, {
            loadPersonData(it)
            detailContentBinding.pbDetails.visibility = View.GONE
        })

        viewModel.getVideo()?.observe(this, {
            with(it) {
                val link = when (site) {
                    Constant.SITE_YOUTUBE -> "${Constant.YOUTUBE_VIDEO_URL}$key"
                    Constant.SITE_VIMEO -> "${Constant.VIMEO_VIDEO_URL}$key"
                    else -> null
                }

                if (link != null) {
                    detailContentBinding.btnTrailer.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                        startActivity(intent)
                    }
                }
            }
        })
    }

    private fun loadPersonData(persons: List<CreditEntity>) {
        val detailAdapter = DetailPersonAdapter()
        detailAdapter.setPersons(persons)

        with(detailContentBinding.rvPerson) {
            layoutManager = GridLayoutManager(context, Constant.GRID_SPAN_COUNT)
            setHasFixedSize(true)
            adapter = detailAdapter
        }
    }

    private fun loadCatalogData() {
        supportActionBar?.title = catalog.title

        detailContentBinding.tvCatalogTitle.text = catalog.title

        if (TextUtils.isEmpty(catalog.overview)) {
            detailContentBinding.tvOverview.visibility = View.GONE
            detailContentBinding.tvOverviewText.visibility = View.GONE
        } else {
            detailContentBinding.tvOverviewText.text = catalog.overview
        }

        Glide.with(this)
            .load(catalog.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imgPoster)
    }
}