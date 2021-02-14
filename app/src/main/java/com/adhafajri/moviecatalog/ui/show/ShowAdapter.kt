package com.adhafajri.moviecatalog.ui.show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.databinding.ItemsCatalogBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ShowAdapter : RecyclerView.Adapter<ShowAdapter.CatalogViewHolder>() {
    private var listCatalogs = ArrayList<CatalogEntity>()

    fun setCatalogs(catalog: List<CatalogEntity>?) {
        if (catalog == null) return
        this.listCatalogs.clear()
        this.listCatalogs.addAll(catalog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val itemsCatalogBinding = ItemsCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(itemsCatalogBinding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val catalog = listCatalogs[position]
        holder.bind(catalog)
    }

    override fun getItemCount(): Int = listCatalogs.size

    class CatalogViewHolder(private val binding: ItemsCatalogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(catalog: CatalogEntity) {
            with(binding) {
                tvItemTitle.text = catalog.title
                Glide.with(itemView.context)
                        .load(catalog.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}