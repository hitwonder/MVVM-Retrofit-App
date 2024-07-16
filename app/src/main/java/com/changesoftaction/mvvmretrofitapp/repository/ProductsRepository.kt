package com.changesoftaction.mvvmretrofitapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.changesoftaction.mvvmretrofitapp.model.GetProducts
import com.changesoftaction.mvvmretrofitapp.network.ApiInterface

class ProductsRepository(private val apiInterface: ApiInterface) {

    private val productsMutable = MutableLiveData<Response<List<GetProducts>>>()

    val productsLiveData: LiveData<Response<List<GetProducts>>>
        get() = productsMutable


    suspend fun getProducts() {
        val getProducts = apiInterface.getProducts()
        if (getProducts.isSuccessful) {

            try {
                productsMutable.postValue(Response.Success(getProducts.body()))
            } catch (e: Exception) {
                productsMutable.postValue(Response.Error(e.message.toString()))
            }
        } else {
            productsMutable.postValue(Response.Error("Api error occured"))
        }

    }

}