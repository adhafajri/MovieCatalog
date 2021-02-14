package com.adhafajri.moviecatalog.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.databinding.ActivityDetailBinding
import com.adhafajri.moviecatalog.databinding.ContentDetailBinding
import com.adhafajri.moviecatalog.utils.Constant

class DetailActivity : AppCompatActivity() {
    private lateinit var detailContentBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val catalogId = extras.getString(Constant.EXTRA_CATALOG)
        }
    }
}