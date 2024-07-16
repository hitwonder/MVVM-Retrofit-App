package com.changesoftaction.mvvmretrofitapp.model

data class GetProducts(
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
)