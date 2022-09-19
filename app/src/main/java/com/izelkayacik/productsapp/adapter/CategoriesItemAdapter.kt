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
import com.izelkayacik.productsapp.adapter.listeners.DetailClickEvent
import com.izelkayacik.productsapp.model.categories.CategoriesDetail

class CategoriesItemAdapter(
    private val categoriesList: List<CategoriesDetail>,
    private val ctx: Context,
    private val detailClickEvent: DetailClickEvent
) : RecyclerView.Adapter<CategoriesItemAdapter.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoriesName: TextView = view.findViewById(R.id.txtCategoriesName)
        val categoriesImage: AppCompatImageView = view.findViewById(R.id.imgCategories)
        val itemLayout: MaterialCardView = view.findViewById(R.id.itemLayout)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val txtDiscountPrice: TextView = view.findViewById(R.id.txtDiscountPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_item_view, parent, false)

        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val currentCategories: CategoriesDetail? = categoriesList[position]

        Glide.with(ctx).load(currentCategories?.icon).into(holder.categoriesImage)

        currentCategories?.name.let {
            holder.categoriesName.text = it
        }

        holder.txtPrice.visibility = View.GONE
        holder.txtDiscountPrice.visibility = View.GONE

        holder.itemLayout.setOnClickListener {
            currentCategories?.let { item -> detailClickEvent.click(item.categoryId) }
        }
    }
    override fun getItemCount(): Int {
        return categoriesList.size
    }
}

