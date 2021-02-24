package com.adhafajri.moviecatalog.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhafajri.moviecatalog.data.source.local.entity.PersonJobEntity
import com.adhafajri.moviecatalog.databinding.ItemsTitleBinding

class DetailTitleAdapter : RecyclerView.Adapter<DetailTitleAdapter.TitleViewHolder>() {
    private var listTitles = ArrayList<PersonJobEntity>()

    fun setJobs(jobs: List<PersonJobEntity>?) {
        if (jobs == null) return
        this.listTitles.clear()
        this.listTitles.addAll(jobs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val itemsTitleBinding =
            ItemsTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TitleViewHolder(itemsTitleBinding)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {

        val title = listTitles[position]
        holder.bind(title)
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }

    class TitleViewHolder(private val binding: ItemsTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonJobEntity) {
            with(binding) {
                tvTitle.text = person.job
            }
        }
    }
}