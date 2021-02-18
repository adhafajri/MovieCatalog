package com.adhafajri.moviecatalog.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhafajri.moviecatalog.data.PersonEntity
import com.adhafajri.moviecatalog.databinding.ItemsPersonBinding

class
DetailAdapter : RecyclerView.Adapter<DetailAdapter.CatalogViewHolder>() {
    private var listPersons = ArrayList<PersonEntity>()

    fun setPersons(persons: List<PersonEntity>?) {
        if (persons == null) return
        this.listPersons.clear()
        this.listPersons.addAll(persons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val itemsPersonBinding =
            ItemsPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(itemsPersonBinding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val person = listPersons[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = listPersons.size

    class CatalogViewHolder(private val binding: ItemsPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonEntity) {
            with(binding) {
                tvName.text = person.name
            }
        }
    }
}