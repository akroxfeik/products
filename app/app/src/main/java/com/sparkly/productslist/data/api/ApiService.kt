package com.sparkly.productslist.data.api

import com.sparkly.productslist.data.model.ResponseProduct
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<ResponseProduct>
}