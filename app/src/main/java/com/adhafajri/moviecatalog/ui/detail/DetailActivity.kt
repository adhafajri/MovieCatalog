package com.adhafajri.moviecatalog.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.CatalogEntity
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

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogId = extras.getString(Constant.EXTRA_CATALOG_ID)
            val type = extras.getString(Constant.EXTRA_TYPE)
            if (catalogId != null && type != null) {
                viewModel.setSelectedCatalog(catalogId, type)
                loadCatalogData(viewModel.getCatalog() as CatalogEntity)
            }
        }
    }

    private fun loadCatalogData(catalogEntity: CatalogEntity) {
        supportActionBar?.title = catalogEntity.title

        detailContentBinding.tvTitle.text = catalogEntity.title
        detailContentBinding.tvYear.text = catalogEntity.year

        if (TextUtils.isEmpty(catalogEntity.synopsis)) {
            detailContentBinding.tvSynopsis.visibility = View.GONE
            detailContentBinding.tvSynopsisText.visibility = View.GONE
        } else {
            detailContentBinding.tvSynopsisText.text = catalogEntity.synopsis
        }

        if (TextUtils.isEmpty(catalogEntity.directors)) {
            detailContentBinding.tvDirectors.visibility = View.GONE
            detailContentBinding.tvDirectorsText.visibility = View.GONE
        } else {
            detailContentBinding.tvDirectorsText.text = catalogEntity.directors
        }

        if (TextUtils.isEmpty(catalogEntity.writers)) {
            detailContentBinding.tvWriters.visibility = View.GONE
            detailContentBinding.tvWritersText.visibility = View.GONE
        } else {
            detailContentBinding.tvWritersText.text = catalogEntity.writers
        }

        if (TextUtils.isEmpty(catalogEntity.stars)) {
            detailContentBinding.tvStars.visibility = View.INVISIBLE
            detailContentBinding.tvStarsText.visibility = View.INVISIBLE
        } else {
            detailContentBinding.tvStarsText.text = catalogEntity.stars
        }

        Glide.with(this)
            .load(catalogEntity.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imgPoster)

        detailContentBinding.btnTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(catalogEntity.trailerPath))
            startActivity(intent)
        }
    }
}