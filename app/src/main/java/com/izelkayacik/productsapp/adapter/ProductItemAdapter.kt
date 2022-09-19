package com.izelkayacik.productsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.izelkayacik.productsapp.R
import com.izelkayacik.productsapp.model.product.Data
import com.izelkayacik.productsapp.adapter.listeners.ProductClickEvent

class ProductItemAdapter(
    private val productList: List<Data>?,
    private val ctx: Context,
    private val productClickEvent: ProductClickEvent
) : RecyclerView.Adapter<ProductItemAdapter.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoriesName: TextView = view.findViewById(R.id.txtCategoriesName)
        val productImage: AppCompatImageView = view.findViewById(R.id.imgCategories)
        val productPrice: TextView = view.findViewById(R.id.txtPrice)
        val productDiscountPrice: TextView = view.findViewById(R.id.txtDiscountPrice)
        val itemLayout: MaterialCardView = view.findViewById(R.id.itemLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_item_view, parent, false)

        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val currentProduct: Data? = productList?.get(position)

        Glide.with(ctx).load(currentProduct?.images?.get(0)?.t).into(holder.productImage)

        currentProduct?.title.let {
            holder.categoriesName.text = it
        }
        currentProduct?.price.let {
            holder.productPrice.text = it.toString()
        }
        holder.productDiscountPrice.visibility = View.GONE
        holder.itemLayout.setOnClickListener {
            currentProduct?.let { item ->
                productClickEvent.click(item.id)
            }
        }
    }
    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }
}