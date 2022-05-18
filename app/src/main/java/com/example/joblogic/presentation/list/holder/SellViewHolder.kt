package com.example.joblogic.presentation.list.holder

import com.example.joblogic.databinding.ItemProductBinding
import com.example.joblogic.presentation.list.ItemData

class SellViewHolder(private val binding: ItemProductBinding) : BasisViewHolder(binding) {
    override fun bind(listData: ItemData) {
        val data = listData as ItemData.ProductItem
        binding.product = data
    }
}