package com.star.war.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.star.war.R
import com.star.war.databinding.ItemCharacterBinding
import com.star.war.repo.model.SearchCharacter
import com.star.war.ui.base.BaseRecyclerViewAdapter
import com.star.war.ui.helper.OnItemClickListener

class SearchItemAdapter(
    private val onItemClickListener: OnItemClickListener<SearchCharacter>
) : BaseRecyclerViewAdapter<SearchItemAdapter.SearchViewHolder>() {

    private val items = mutableListOf<SearchCharacter>()

    override fun getItemCount(): Int = items.size

    fun setItems(searchCharacters: List<SearchCharacter>) {
        items.clear()
        items.addAll(searchCharacters)
        if(attachedToRecyclerView) notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(parent.getViewBinding(R.layout.item_character))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position], onItemClickListener)
    }

    inner class SearchViewHolder(val binding: ItemCharacterBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(searchCharacter: SearchCharacter, onItemClickListener: OnItemClickListener<SearchCharacter>) {
                    binding.item = searchCharacter
                    binding.clItem.setOnClickListener {
                        onItemClickListener.onItemClicked(searchCharacter)
                    }
                }
            }
}