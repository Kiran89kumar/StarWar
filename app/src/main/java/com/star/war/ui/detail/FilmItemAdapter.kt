package com.star.war.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.star.war.R
import com.star.war.databinding.ItemFilmBinding
import com.star.war.repo.model.FilmResponse
import com.star.war.ui.base.BaseRecyclerViewAdapter

class FilmItemAdapter: BaseRecyclerViewAdapter<FilmItemAdapter.FilmViewHolder>() {

    private val items = mutableListOf<FilmResponse>()

    override fun getItemCount(): Int = items.size

    fun setItems(filmResponses: List<FilmResponse>) {
        items.clear()
        items.addAll(filmResponses)
        if(attachedToRecyclerView) notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(parent.getViewBinding(R.layout.item_film))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class FilmViewHolder(val binding: ItemFilmBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(filmResponse: FilmResponse) {
                    binding.item = filmResponse
                }
            }
}