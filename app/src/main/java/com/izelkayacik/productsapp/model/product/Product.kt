package com.izelkayacik.productsapp.model.product


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("success")
    val success: Boolean
)