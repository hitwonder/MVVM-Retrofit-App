package com.changesoftaction.mvvmretrofitapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.changesoftaction.mvvmretrofitapp.model.GetProducts
import com.changesoftaction.mvvmretrofitapp.network.ApiClient
import com.changesoftaction.mvvmretrofitapp.network.ApiInterface
import com.changesoftaction.mvvmretrofitapp.repository.ProductsRepository
import com.changesoftaction.mvvmretrofitapp.repository.Response
import com.changesoftaction.mvvmretrofitapp.ui.adapter.ProductAdapter
import com.changesoftaction.mvvmretrofitapp.viewmodel.ProductsViewModel
import com.changesoftaction.mvvmretrofitapp.viewmodel.ProductsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var productAdapter: ProductAdapter
    private lateinit var rvProduct: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProduct = findViewById(R.id.rvProduct)
        progressBar = findViewById(R.id.progressBar)

        val apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)
        val productsRepository = ProductsRepository(apiInterface)

        productsViewModel = ViewModelProvider(
            this,
            ProductsViewModelFactory(productsRepository)
        )[ProductsViewModel::class.java]
        productsViewModel.mutableLiveData.observe(this) { response ->

            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        setupRecycleView(it)
                    }
                    progressBar.visibility = View.GONE
                }

                is Response.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is Response.Error -> {
                    Toast.makeText(this, " ${response.error} ", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }

            }

        }


    }

    private fun setupRecycleView(getProducts: List<GetProducts>) {
        rvProduct.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(getProducts)
        rvProduct.adapter = productAdapter

    }
}