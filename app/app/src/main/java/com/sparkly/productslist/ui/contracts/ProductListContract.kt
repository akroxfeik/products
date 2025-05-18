package com.sparkly.productslist.ui.contracts

import com.sparkly.productslist.data.model.Product

class ProductListContract {
    data class State (
        val list: List<Product> = listOf(),
        val isLoading: Boolean = false,
        val isConnected: Boolean = false
    )
}