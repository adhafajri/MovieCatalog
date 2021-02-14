package com.adhafajri.moviecatalog.ui.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.databinding.FragmentMovieBinding
import com.adhafajri.moviecatalog.databinding.FragmentShowBinding
import com.adhafajri.moviecatalog.ui.movie.MovieAdapter
import com.adhafajri.moviecatalog.ui.movie.MovieViewModel
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data

class ShowFragment : Fragment() {
    private lateinit var fragmentShowBinding: FragmentShowBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater, container, false)
        return fragmentShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ShowViewModel::class.java]
            val catalogs = viewModel.getShowCatalogs()
            val movieAdapter = MovieAdapter()
            movieAdapter.setCatalogs(catalogs)

            with(fragmentShowBinding.rvShow) {
                layoutManager = GridLayoutManager(context, Constant.GRID_SPAN_COUNT)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}