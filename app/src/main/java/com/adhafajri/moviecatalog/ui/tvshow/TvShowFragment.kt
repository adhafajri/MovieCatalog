package com.adhafajri.moviecatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhafajri.moviecatalog.databinding.FragmentTvShowBinding
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory
import com.adhafajri.moviecatalog.vo.Status

class TvShowFragment : Fragment() {
    private var fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = fragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(activity!!)
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            loadPopularTvShows(viewModel)
            loadTodayAiringTvShows(viewModel)
        }
    }

    private fun loadTodayAiringTvShows(viewModel: TvShowViewModel) {
        val upcomingTvShowAdapter = TvShowAdapter()
        viewModel.getTodayAiringTvShows().observe(this, Observer { catalogResource ->
            if (catalogResource != null) {
                when (catalogResource.status) {
                    Status.LOADING -> binding?.pbTvShowAiring?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.pbTvShowAiring?.visibility = View.GONE
                        upcomingTvShowAdapter.submitList(catalogResource.data)
                    }
                    Status.ERROR -> {
                        binding?.pbTvShowAiring?.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Failed to load Upcoming TV Shows",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        with(binding?.rvTvShowAiring) {
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = upcomingTvShowAdapter
        }
    }

    private fun loadPopularTvShows(viewModel: TvShowViewModel) {
        val popularTvShowAdapter = TvShowAdapter()
        viewModel.getPopularTvShows().observe(this, Observer { catalogResource ->
            if (catalogResource != null) {
                when (catalogResource.status) {
                    Status.LOADING -> binding?.pbTvShowPopular?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.pbTvShowPopular?.visibility = View.GONE
                        popularTvShowAdapter.submitList(catalogResource.data)
                    }
                    Status.ERROR -> {
                        binding?.pbTvShowPopular?.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Failed to load Popular TV Shows",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        with(binding?.rvTvShowPopular) {
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = popularTvShowAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentTvShowBinding = null
    }
}