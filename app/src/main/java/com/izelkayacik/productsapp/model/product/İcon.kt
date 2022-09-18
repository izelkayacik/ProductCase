package com.izelkayacik.productsapp.model.product


import com.google.gson.annotations.SerializedName

data class İcon(
    @SerializedName("n")
    val n: String,
    @SerializedName("t")
    val t: String
)