package com.changesoftaction.mvvmretrofitapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changesoftaction.mvvmretrofitapp.model.GetProducts
import com.changesoftaction.mvvmretrofitapp.repository.ProductsRepository
import com.changesoftaction.mvvmretrofitapp.repository.Response
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    val mutableLiveData: LiveData<Response<List<GetProducts>>>
        get() = productsRepository.productsLiveData


    init {
        viewModelScope.launch {
            productsRepository.getProducts()
        }

    }


}