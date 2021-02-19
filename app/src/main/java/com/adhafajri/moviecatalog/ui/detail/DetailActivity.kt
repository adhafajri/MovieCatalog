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
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.data.PersonEntity
import com.adhafajri.moviecatalog.databinding.ActivityDetailBinding
import com.adhafajri.moviecatalog.databinding.ContentDetailBinding
import com.adhafajri.moviecatalog.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {
    private lateinit var detailContentBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogId = extras.getString(Constant.EXTRA_CATALOG_ID)
            val type = extras.getString(Constant.EXTRA_TYPE)
            if (catalogId != null && type != null) {
                viewModel.setSelectedCatalog(catalogId)
                loadCatalogData(
                    viewModel.getCatalog() as CatalogEntity,
                    viewModel.getPersons() as ArrayList<PersonEntity>
                )
            }
        }
    }

    private fun loadCatalogData(
        catalog: CatalogEntity,
        persons: ArrayList<PersonEntity>
    ) {
        supportActionBar?.title = catalog.title

        detailContentBinding.tvTitle.text = catalog.title
        detailContentBinding.tvYear.text = catalog.year

        if (TextUtils.isEmpty(catalog.overview)) {
            detailContentBinding.tvOverview.visibility = View.GONE
            detailContentBinding.tvOverviewText.visibility = View.GONE
        } else {
            detailContentBinding.tvOverviewText.text = catalog.overview
        }

        val detailAdapter = DetailPersonAdapter()
        detailAdapter.setPersons(persons)

        with(detailContentBinding.rvPerson) {
            layoutManager = GridLayoutManager(context, Constant.GRID_SPAN_COUNT)
            setHasFixedSize(true)
            adapter = detailAdapter
        }

        Glide.with(this)
            .load(catalog.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imgPoster)

        detailContentBinding.btnTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(catalog.trailerPath))
            startActivity(intent)
        }
    }
}