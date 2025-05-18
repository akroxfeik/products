package com.sparkly.productslist.data.repository

import com.sparkly.productslist.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper){
    suspend fun getProducts() = apiHelper.getProducts()
}