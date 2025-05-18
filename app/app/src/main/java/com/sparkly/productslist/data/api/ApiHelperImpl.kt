package com.sparkly.productslist.data.api

import com.sparkly.productslist.data.model.ResponseProduct
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getProducts(): Response<ResponseProduct> = apiService.getProducts()
}