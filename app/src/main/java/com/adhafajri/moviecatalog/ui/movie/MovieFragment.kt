package com.adhafajri.moviecatalog.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhafajri.moviecatalog.databinding.FragmentMovieBinding
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory
import com.adhafajri.moviecatalog.vo.Status

class MovieFragment : Fragment() {
    private var fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = fragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(activity!!)
            val viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]

            loadPopularMovies(viewModel)
            loadUpcomingMovies(viewModel)
        }
    }

    private fun loadUpcomingMovies(viewModel: MovieViewModel) {
        val upcomingMovieAdapter = MovieAdapter()
        viewModel.getUpcomingMovies().observe(this, Observer { catalogResource ->
            if (catalogResource != null) {
                when (catalogResource.status) {
                    Status.LOADING -> binding?.pbMovieUpcoming?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.pbMovieUpcoming?.visibility = View.GONE
                        upcomingMovieAdapter.submitList(catalogResource.data)
                    }
                    Status.ERROR -> {
                        binding?.pbMovieUpcoming?.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Failed to load Upcoming Movies",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        with(binding?.rvMovieUpcoming) {
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = upcomingMovieAdapter
        }
    }

    private fun loadPopularMovies(viewModel: MovieViewModel) {
        val popularMovieAdapter = MovieAdapter()
        viewModel.getPopularMovies().observe(this, Observer { catalogResource ->
            if (catalogResource != null) {
                when (catalogResource.status) {
                    Status.LOADING -> binding?.pbMoviePopular?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.pbMoviePopular?.visibility = View.GONE
                        popularMovieAdapter.submitList(catalogResource.data)
                    }
                    Status.ERROR -> {
                        binding?.pbMoviePopular?.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Failed to load Popular Movies",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        with(binding?.rvMoviePopular) {
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = popularMovieAdapter
        }
    }
}