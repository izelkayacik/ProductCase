package com.izelkayacik.productsapp.model.categories


import com.google.gson.annotations.SerializedName

data class CategoriesDetail(
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("createDate")
    val createDate: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("orderIndex")
    val orderIndex: Int,
    @SerializedName("subCategories")
    val subCategories: List<Any>,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("updateDate")
    val updateDate: String
)