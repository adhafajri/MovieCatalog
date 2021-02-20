package com.adhafajri.moviecatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adhafajri.moviecatalog.databinding.FragmentTvShowBinding
import com.adhafajri.moviecatalog.utils.Constant

class TvShowFragment : Fragment() {
    private lateinit var fragmentShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]
            val catalogs = viewModel.getTvShowCatalogs()
            val showAdapter = TvShowAdapter()
            showAdapter.setCatalogs(catalogs)

            with(fragmentShowBinding.rvShow) {
                layoutManager = GridLayoutManager(context, Constant.GRID_SPAN_COUNT)
                setHasFixedSize(true)
                adapter = showAdapter
            }
        }
    }
}