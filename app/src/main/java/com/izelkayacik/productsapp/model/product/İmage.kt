package com.izelkayacik.productsapp.model.product


import com.google.gson.annotations.SerializedName

data class İmage(
    @SerializedName("n")
    val n: String,
    @SerializedName("t")
    val t: String
)