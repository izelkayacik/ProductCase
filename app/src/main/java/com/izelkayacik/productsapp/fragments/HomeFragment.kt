package com.izelkayacik.productsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izelkayacik.productsapp.R
import com.izelkayacik.productsapp.adapter.CategoriesItemAdapter
import com.izelkayacik.productsapp.model.Categories
import com.izelkayacik.productsapp.model.CategoriesDetail
import com.izelkayacik.productsapp.service.Retrofit
import com.izelkayacik.productsapp.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var rvCategories: RecyclerView
    private lateinit var categoriesDetailModels: List<CategoriesDetail>
    private lateinit var categoriesItemAdapter: CategoriesItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        rvCategories = view.findViewById<RecyclerView>(R.id.rvCategories)
        loadData()

        return view

    }

    private fun loadData() {
        Retrofit.getClient().getData().enqueue(object : Callback<Categories>{
            override fun onResponse(
                call: Call<Categories>,
                response: Response<Categories>
            ) {
                if (response.isSuccessful) {

                    //println(response.body())

                    response.body()?.data.let {

                        categoriesDetailModels = it!!

                        categoriesItemAdapter =
                            CategoriesItemAdapter(categoriesDetailModels!!, MainActivity.ctx)
                        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
                            MainActivity.ctx, 2
                        )
                        rvCategories.layoutManager = layoutManager
                        rvCategories.adapter = categoriesItemAdapter

                    }
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }

    }
}