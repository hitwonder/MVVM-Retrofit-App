package com.changesoftaction.mvvmretrofitapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.changesoftaction.mvvmretrofitapp.R
import com.changesoftaction.mvvmretrofitapp.model.GetProducts

class ProductAdapter(val products: List<GetProducts>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val viewInflater = layoutInflater.inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(viewInflater)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.tvName.text = products[position].title
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    var tvName: TextView = item.findViewById(R.id.tvName)

}