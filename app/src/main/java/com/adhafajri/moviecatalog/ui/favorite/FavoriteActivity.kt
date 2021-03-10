package com.adhafajri.moviecatalog.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adhafajri.moviecatalog.databinding.ActivityFavoriteBinding
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory


class FavoriteActivity : AppCompatActivity() {
    private var activityFavoriteBinding: ActivityFavoriteBinding? = null
    private val binding get() = activityFavoriteBinding

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[FavoriteViewModel::class.java]

        loadFavoriteCatalogs()
    }

    private fun loadFavoriteCatalogs() {
        adapter = FavoriteAdapter()
        binding?.pbFavoriteCatalogs?.visibility = View.VISIBLE
        viewModel.getFavorites().observe(this, Observer { catalogs ->
            binding?.pbFavoriteCatalogs?.visibility = View.GONE
            adapter.submitList(catalogs)
        })

        with(binding?.rvCatalog) {
            this?.layoutManager = GridLayoutManager(
                this@FavoriteActivity,
                Constant.GRID_SPAN_COUNT,
                GridLayoutManager.VERTICAL,
                false
            )
            this?.setHasFixedSize(true)
            this?.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityFavoriteBinding = null
    }
}