package com.sparkly.productslist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkly.productslist.data.model.Product
import com.sparkly.productslist.data.repository.MainRepository
import com.sparkly.productslist.ui.contracts.ProductListContract
import com.sparkly.productslist.utils.NetworkHelper
import com.sparkly.productslist.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val repo: MainRepository,
    private val networkHelper: NetworkHelper,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {
    var state by mutableStateOf(
        ProductListContract.State(
            list = listOf(),
            isLoading = true,
            isConnected = true
        )
    )

    init {
        viewModelScope.launch { getProducts() }
    }

    private fun getProducts() {
        viewModelScope.launch {
            val publishList: List<Product>
            var isConnected = networkHelper.isNetworkConnected()

            val products = sharedPreferencesManager.getProductList()
            if(products != null && products.isNotEmpty()) {
                isConnected = true
                publishList = products
            } else {
                val response = if(isConnected) repo.getProducts() else null
                publishList = response?.body()?.products ?: listOf()
                sharedPreferencesManager.saveProductList(response?.body()?.products!!)
            }

            state = state.copy(
                list = publishList,
                isLoading = false,
                isConnected = isConnected
            )
        }
    }
}