package com.izelkayacik.productsapp.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izelkayacik.productsapp.R
import com.izelkayacik.productsapp.adapter.CategoriesItemAdapter
import com.izelkayacik.productsapp.model.Categories
import com.izelkayacik.productsapp.model.CategoriesDetail
import com.izelkayacik.productsapp.service.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoriesListActivity : AppCompatActivity() {

    lateinit var rvCategories: RecyclerView
    private lateinit var categoriesDetailModels: Categories
    private lateinit var categoriesItemAdapter: CategoriesItemAdapter
    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_list)
        ctx = this;

        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)

        loadData()
    }

    private fun loadData() {

        Retrofit.getClient().getData().enqueue(object : Callback<Categories> {
            override fun onResponse(
                call: Call<Categories>,
                response: Response<Categories>
            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        categoriesDetailModels = ArrayList(it)
//
//                        categoriesItemAdapter = CategoriesItemAdapter(categoriesDetailModels!!, ctx)
//                        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(ctx, 2)
//                        rvCategories.layoutManager = layoutManager
//                        rvCategories.adapter = categoriesItemAdapter
//
//                        for (categoriesDetailModel: CategoriesDetail in categoriesDetailModels!!) {
//
//                            println(categoriesDetailModel.categoryId)
//
//                        }
//                    }
//
//                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}