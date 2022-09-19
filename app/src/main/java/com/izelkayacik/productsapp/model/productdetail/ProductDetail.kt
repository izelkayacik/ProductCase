package com.izelkayacik.productsapp.model.productdetail


import com.google.gson.annotations.SerializedName

data class ProductDetail(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("success")
    val success: Boolean?
)