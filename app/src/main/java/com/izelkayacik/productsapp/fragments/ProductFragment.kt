package com.izelkayacik.productsapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izelkayacik.productsapp.R
import com.izelkayacik.productsapp.adapter.listeners.ProductClickEvent
import com.izelkayacik.productsapp.adapter.ProductItemAdapter
import com.izelkayacik.productsapp.model.product.Data
import com.izelkayacik.productsapp.model.product.Product
import com.izelkayacik.productsapp.service.Retrofit
import com.izelkayacik.productsapp.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {

    lateinit var rvProducts: RecyclerView
    private  var dataModels: List<Data>? = null
    private lateinit var productItemAdapter: ProductItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_product, container, false)
        val categoriId = ProductFragmentArgs.fromBundle(requireArguments()).categoriId

        rvProducts = view.findViewById<RecyclerView>(R.id.rvProductsC)
        loadData(categoriId, "Price")

        view.setOnClickListener {
            findNavController().navigate(R.id.actionProductToProductDetail)
        }
        return view

    }

    private fun loadData(categoriId: String?, sort: String?) {

        Retrofit.getClient().getProduct(categoriId, sort)?.enqueue(object : Callback<Product?> {
            override fun onResponse(call: Call<Product?>, response: Response<Product?>) {
                if (response.isSuccessful) {

                    response.body()?.data.let {

                        dataModels = it

                        productItemAdapter =
                            ProductItemAdapter(dataModels,
                                MainActivity.ctx,
                                object : ProductClickEvent {
                                    override fun click(id: String) {
                                        val direction =
                                            ProductFragmentDirections
                                                .actionProductToProductDetail(id)

                                        findNavController().navigate(direction)
                                    }
                                })

                        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
                            MainActivity.ctx, 2
                        )
                        rvProducts.layoutManager = layoutManager
                        rvProducts.adapter = productItemAdapter
                    }
                }
            }

            override fun onFailure(call: Call<Product?>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

