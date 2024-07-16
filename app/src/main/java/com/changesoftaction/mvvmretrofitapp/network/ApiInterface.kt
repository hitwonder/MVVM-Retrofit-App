package com.changesoftaction.mvvmretrofitapp.network

import com.changesoftaction.mvvmretrofitapp.model.GetProducts
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/products")
    suspend fun getProducts(): Response<List<GetProducts>>

}