package com.izelkayacik.productsapp.model


import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("data")
    val data: List<CategoriesDetail>,
    @SerializedName("success")
    val success: Boolean
)