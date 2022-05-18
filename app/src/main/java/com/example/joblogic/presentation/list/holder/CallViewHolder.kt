package com.example.joblogic.presentation.list.holder

import com.example.joblogic.databinding.ItemContactBinding
import com.example.joblogic.presentation.list.ItemData

class CallViewHolder(private val binding: ItemContactBinding) : BasisViewHolder(binding) {
    override fun bind(listData: ItemData) {
        val data = listData as ItemData.ContactItem
        binding.contact = data
    }
}