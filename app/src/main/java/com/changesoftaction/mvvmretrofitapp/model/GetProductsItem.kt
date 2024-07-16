package com.changesoftaction.mvvmretrofitapp.model

data class GetProductsItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)