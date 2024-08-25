package com.example.accordionlistapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.accordionlistapplication.databinding.RowItemListBinding

class ItemListViewHolder(private val binding: RowItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val expandableText = binding.description
    val imageIcon = binding.expandableIcon
    fun bind(item: ItemList) {
        binding.title.text = item.title
        binding.description.text = item.description
        binding.icon.setImageResource(
            binding.icon.context.resources.getIdentifier(
                item.icon,
                "drawable",
                binding.icon.context.packageName
            )
        )
    }
}