package com.adhafajri.moviecatalog.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity
import com.adhafajri.moviecatalog.databinding.ItemsPersonBinding

class
DetailPersonAdapter : RecyclerView.Adapter<DetailPersonAdapter.PersonViewHolder>() {
    private var listPersons = ArrayList<PersonEntity>()

    fun setPersons(persons: ArrayList<PersonEntity>?) {
        if (persons == null) return
        this.listPersons.clear()
        this.listPersons.addAll(persons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemsPersonBinding =
            ItemsPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(itemsPersonBinding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = listPersons[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = listPersons.size

    class PersonViewHolder(private val binding: ItemsPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonEntity) {
            with(binding) {
                tvName.text = person.name
            }
        }
    }
}