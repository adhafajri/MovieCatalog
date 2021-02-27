package com.adhafajri.moviecatalog.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhafajri.moviecatalog.data.source.local.entity.PersonJobEntity
import com.adhafajri.moviecatalog.databinding.ItemsJobBinding

class DetailJobAdapter : RecyclerView.Adapter<DetailJobAdapter.JobViewHolder>() {
    private var listTitles = ArrayList<PersonJobEntity>()

    fun setJobs(jobs: List<PersonJobEntity>?) {
        if (jobs == null) return
        this.listTitles.clear()
        this.listTitles.addAll(jobs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val itemsJobBinding =
            ItemsJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(itemsJobBinding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {

        val title = listTitles[position]
        holder.bind(title)
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }

    class JobViewHolder(private val binding: ItemsJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonJobEntity) {
            with(binding) {
                tvTitle.text = person.job
            }
        }
    }
}