package com.example.joblogic.data.datasources.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class ProductModel(
    @PrimaryKey
    // declare sell list items
    val id: Int,
    val name: String,
    val price: Long,
    val quantity: Int,
    val type: Int
)