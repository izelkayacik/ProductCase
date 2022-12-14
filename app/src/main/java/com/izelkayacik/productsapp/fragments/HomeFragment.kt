package com.izelkayacik.productsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izelkayacik.productsapp.R
import com.izelkayacik.productsapp.adapter.CategoriesItemAdapter
import com.izelkayacik.productsapp.adapter.listeners.DetailClickEvent
import com.izelkayacik.productsapp.model.categories.Categories
import com.izelkayacik.productsapp.model.categories.CategoriesDetail
import com.izelkayacik.productsapp.service.Retrofit
import com.izelkayacik.productsapp.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

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

        rvCategories = view.findViewById<RecyclerView>(R.id.rvCategoriesC)
        loadData()

        view.setOnClickListener {
            findNavController().navigate(R.id.actionHomeFragmentToProductFragment)
        }
        return view
    }

    private fun loadData() {
        Retrofit.getClient().getData().enqueue(object : Callback<Categories> {
            override fun onResponse(
                call: Call<Categories>,
                response: Response<Categories>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data.let {
                        categoriesDetailModels = it!!
                        categoriesItemAdapter =
                            CategoriesItemAdapter(
                                categoriesDetailModels!!,
                                MainActivity.ctx,
                                object : DetailClickEvent {
                                    override fun click(id: String) {
                                        val direction = HomeFragmentDirections
                                            .actionHomeFragmentToProductFragment(id)
                                        findNavController().navigate(direction)
                                    }

                                })
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
}