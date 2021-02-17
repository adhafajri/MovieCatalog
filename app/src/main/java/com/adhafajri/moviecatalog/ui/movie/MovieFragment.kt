package com.adhafajri.moviecatalog.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adhafajri.moviecatalog.databinding.FragmentMovieBinding
import com.adhafajri.moviecatalog.utils.Constant

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val catalogs = viewModel.getMovieCatalogs()

            val movieAdapter = MovieAdapter()
            movieAdapter.setCatalogs(catalogs)

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context, Constant.GRID_SPAN_COUNT)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}