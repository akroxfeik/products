package com.sparkly.productslist.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sparkly.productslist.data.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("product_list_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Use a constant for the key
    private val PRODUCT_LIST_KEY = "product_list"

    fun saveProductList(products: List<Product>) {
        val productsJson = gson.toJson(products)
        sharedPrefs.edit().putString(PRODUCT_LIST_KEY, productsJson).apply()
    }

    fun getProductList(): List<Product> {
        val productsJson = sharedPrefs.getString(PRODUCT_LIST_KEY, null) ?: return emptyList()
        val type: Type = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(productsJson, type) ?: emptyList()
    }
}