package com.izelkayacik.productsapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class Retrofit {

    companion object {
        private val BASE_URL = "https://api.shopiroller.com/v2.0/"

        fun getClient(): ProductApi {

            var retro: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retro.create(ProductApi::class.java)

            return retro.create(ProductApi::class.java)

        }
    }
}