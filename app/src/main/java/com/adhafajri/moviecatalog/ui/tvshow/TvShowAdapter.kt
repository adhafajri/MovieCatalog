package com.adhafajri.moviecatalog.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.databinding.ItemsCatalogBinding
import com.adhafajri.moviecatalog.ui.detail.DetailActivity
import com.adhafajri.moviecatalog.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter :
    PagedListAdapter<CatalogEntity, TvShowAdapter.CatalogViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatalogEntity>() {
            override fun areItemsTheSame(oldItem: CatalogEntity, newItem: CatalogEntity): Boolean {
                return oldItem.catalogId == newItem.catalogId
            }

            override fun areContentsTheSame(
                oldItem: CatalogEntity,
                newItem: CatalogEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val itemsCatalogBinding =
            ItemsCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(itemsCatalogBinding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val catalog = getItem(position)
        if (catalog != null) {
            holder.bind(catalog)
        }
    }

    class CatalogViewHolder(private val binding: ItemsCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(catalog: CatalogEntity) {
            with(binding) {
                tvItemTitle.text = catalog.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(Constant.EXTRA_CATALOG_ID, catalog.catalogId)
                    intent.putExtra(Constant.EXTRA_TYPE, catalog.type)
                    intent.putExtra(Constant.EXTRA_TITLE, catalog.title)
                    intent.putExtra(Constant.EXTRA_OVERVIEW, catalog.overview)
                    intent.putExtra(Constant.EXTRA_POSTER_PATH, catalog.posterPath)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(catalog.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}