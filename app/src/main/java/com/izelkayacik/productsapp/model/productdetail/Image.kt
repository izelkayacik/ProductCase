package com.izelkayacik.productsapp.model.productdetail


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("n")
    val n: String?,
    @SerializedName("t")
    val t: String?
)