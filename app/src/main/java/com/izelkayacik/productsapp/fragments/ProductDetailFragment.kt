package com.izelkayacik.productsapp.fragments

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
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
    private var imageViewIncrease: ImageView? = null
    private var textViewQuantity: TextView? = null
    private var imageViewDecrease: ImageView? = null
    private var quantity = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtProductName = view.findViewById(R.id.txtProductName)
        imgProductDetail = view.findViewById(R.id.imgProductDetail)
        txtPrice = view.findViewById(R.id.txtPrice)
        txtDiscountPrice = view.findViewById(R.id.txtDiscountPrice)
        txtDescription = view.findViewById(R.id.txtDescription)
        itemLayout = view.findViewById(R.id.itemLayout)
        imageViewIncrease = view.findViewById(R.id.imageViewIncrease)
        textViewQuantity = view.findViewById(R.id.textViewQuantity)
        imageViewDecrease = view.findViewById(R.id.imageViewDecrease)

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

                        txtProductName?.text = it.title

                        txtPrice?.text = it.price.toString()

                        imageViewIncrease?.setOnClickListener {
                            quantity++
                            textViewQuantity?.text = quantity.toString()

                        }
                        imageViewDecrease?.setOnClickListener {
                            if (quantity > 1) {
                                quantity--
                                imageViewDecrease?.isClickable = true
                                textViewQuantity?.text = quantity.toString()

                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Adet sayısı 1'den küçük olamaz.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        if (it.campaignPrice != null) {
                            txtDiscountPrice?.text = it.campaignPrice.toString()
                        } else {
                            txtDiscountPrice?.visibility = View.GONE
                        }

                        txtDescription?.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Html.fromHtml(it.description.toString(), Html.FROM_HTML_MODE_COMPACT)
                        } else {
                            Html.fromHtml(it.description.toString())
                        }

                        if (it.isUnLimitedStock == true) {
                            val matrix = ColorMatrix()
                            matrix.setSaturation(0f)
                            val filter = ColorMatrixColorFilter(matrix)

                            imgProductDetail?.setColorFilter(filter)
                        } else {
                            imgProductDetail?.let { it1 ->
                                Glide.with(MainActivity.ctx).load(it.images?.get(0)?.t).into(
                                    it1
                                )
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ProductDetail?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}

