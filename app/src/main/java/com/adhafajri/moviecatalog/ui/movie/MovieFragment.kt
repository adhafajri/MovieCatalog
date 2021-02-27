package com.adhafajri.moviecatalog.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhafajri.moviecatalog.databinding.FragmentMovieBinding
import com.adhafajri.moviecatalog.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
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
        fragmentMovieBinding.pbMovieUpcoming.visibility = View.VISIBLE
        viewModel.getUpcomingMovies().observe(this, {
            fragmentMovieBinding.pbMovieUpcoming.visibility = View.GONE
            upcomingMovieAdapter.setCatalogs(it)
            upcomingMovieAdapter.notifyDataSetChanged()
        })

        with(fragmentMovieBinding.rvMovieUpcoming) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = upcomingMovieAdapter
        }
    }

    private fun loadPopularMovies(viewModel: MovieViewModel) {
        val popularMovieAdapter = MovieAdapter()
        fragmentMovieBinding.pbMoviePopular.visibility = View.VISIBLE
        viewModel.getPopularMovies().observe(this, {
            fragmentMovieBinding.pbMoviePopular.visibility = View.GONE
            popularMovieAdapter.setCatalogs(it)
            popularMovieAdapter.notifyDataSetChanged()
        })

        with(fragmentMovieBinding.rvMoviePopular) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularMovieAdapter
        }
    }
}