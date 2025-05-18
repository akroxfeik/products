package com.sparkly.productslist.data.api

import com.sparkly.productslist.data.model.ResponseProduct
import retrofit2.Response

interface ApiHelper {
    suspend fun getProducts(): Response<ResponseProduct>
}