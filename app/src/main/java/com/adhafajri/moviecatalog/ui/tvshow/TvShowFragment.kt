package com.adhafajri.moviecatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhafajri.moviecatalog.databinding.FragmentTvShowBinding
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            loadPopularTvShows(viewModel)
            loadUpcomingTvShows(viewModel)
        }
    }

    private fun loadUpcomingTvShows(viewModel: TvShowViewModel) {
        val upcomingTvShowAdapter = TvShowAdapter()
        fragmentTvShowBinding.pbTvShowAiring.visibility = View.VISIBLE
        viewModel.getTodayAiringTvShows().observe(this, {
            fragmentTvShowBinding.pbTvShowAiring.visibility = View.GONE
            upcomingTvShowAdapter.setCatalogs(it)
            upcomingTvShowAdapter.notifyDataSetChanged()
        })

        with(fragmentTvShowBinding.rvTvShowAiring) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = upcomingTvShowAdapter
        }
    }

    private fun loadPopularTvShows(viewModel: TvShowViewModel) {
        val popularTvShowAdapter = TvShowAdapter()
        fragmentTvShowBinding.pbTvShowPopular.visibility = View.VISIBLE
        viewModel.getPopularTvShows().observe(this, {
            fragmentTvShowBinding.pbTvShowPopular.visibility = View.GONE
            popularTvShowAdapter.setCatalogs(it)
            popularTvShowAdapter.notifyDataSetChanged()
        })

        with(fragmentTvShowBinding.rvTvShowPopular) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularTvShowAdapter
        }
    }
}