package com.izelkayacik.productsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.izelkayacik.productsapp.R
import com.izelkayacik.productsapp.model.productdetail.Data
import com.izelkayacik.productsapp.model.productdetail.ProductDetail
import com.izelkayacik.productsapp.service.Retrofit
import com.izelkayacik.productsapp.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailFragment : Fragment() {

    private var itemLayout: MaterialCardView? = null
    private var txtProductName: TextView? = null
    private var imgProductDetail: AppCompatImageView? = null
    private var txtPrice: TextView? = null
    private var txtDiscountPrice: TextView? = null
    private var txtDescription: TextView? = null
    private lateinit var productDetailModels: Data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate (R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtProductName = view.findViewById(R.id.txtProductName)
        imgProductDetail = view.findViewById(R.id.imgProductDetail)
        txtPrice = view.findViewById(R.id.txtPrice)
        txtDiscountPrice = view.findViewById(R.id.txtDiscountPrice)
        txtDescription = view.findViewById(R.id.txtDescription)
        itemLayout = view.findViewById(R.id.itemLayout)

        ProductDetailFragmentArgs.fromBundle(requireArguments()).productId?.let {
            loadData(it)
        }
    }

    private fun loadData(id: String?) {
        Retrofit.getClient().getProductDetail(id)?.enqueue(object : Callback<ProductDetail?> {
            override fun onResponse(
                call: Call<ProductDetail?>,
                response: Response<ProductDetail?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        productDetailModels = it

                        //ürün ismi
                        txtProductName?.text = it.title

                        //fiyatı
                        txtPrice?.text = it.price.toString()

                        txtDiscountPrice?.text = it.campaignPrice.toString()

                        //fotoğrafı
                        imgProductDetail?.let { it1 ->
                            Glide.with(MainActivity.ctx).load(it.images?.get(0)?.t).into(
                                it1
                            )
                        }
                        //açıklama
                        txtDescription?.text = it.description

                    }
                }
            }
            override fun onFailure(call: Call<ProductDetail?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}

