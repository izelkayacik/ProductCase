package com.izelkayacik.productsapp.service

import com.izelkayacik.productsapp.model.categories.Categories
import com.izelkayacik.productsapp.model.product.Product
import com.izelkayacik.productsapp.model.productdetail.ProductDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    //https://api.shopiroller.com/v2.0/categories
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Api-Key: xXspnfUxPzOGKNu90bFAjlOTnMLpN8veiixvEFXUw9I=",
        "Alias-Key: AtS1aPFxlIdVLth6ee2SEETlRxk="
    )
    @GET("categories")
    fun getData(): Call<Categories>

    //https://api.shopiroller.com/v2.0/products/advanced-filtered?categoryId=61b1f1c8a82ec0dd1c56f5ef&sort=Price

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Api-Key: xXspnfUxPzOGKNu90bFAjlOTnMLpN8veiixvEFXUw9I=",
        "Alias-Key: AtS1aPFxlIdVLth6ee2SEETlRxk="
    )
    @GET("products/advanced-filtered")
    fun getProduct(
        @Query("categoryId") categoryId: String?,
        @Query("sort") sort: String?
    ): Call<Product?>?

    //https://api.shopiroller.com/v2.0/products/61c3bdc8315b11d58d79f585

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Api-Key: xXspnfUxPzOGKNu90bFAjlOTnMLpN8veiixvEFXUw9I=",
        "Alias-Key: AtS1aPFxlIdVLth6ee2SEETlRxk="
    )
    @GET("products/{id}")
    fun getProductDetail(
        @Path("id") id: String?
    ): Call<ProductDetail?>?
}
