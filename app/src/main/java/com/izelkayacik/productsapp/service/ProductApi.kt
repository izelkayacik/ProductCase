package com.izelkayacik.productsapp.service

import com.izelkayacik.productsapp.model.Categories
import com.izelkayacik.productsapp.model.CategoriesDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

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

}