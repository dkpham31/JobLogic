package com.example.joblogic.domain.entities

data class ProductItem(
    // declare buy list values
    val id: Int,
    val name: String,
    val price: Amount,
    val quantity: Int,
    val type: Int,
)

typealias  ItemToSell = ProductItem
typealias  ItemToBuy = ProductItem
