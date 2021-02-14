package com.adhafajri.moviecatalog.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.databinding.FragmentMovieBinding
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val catalogs = Data.generateCatalogs(Constant.MOVIES)
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