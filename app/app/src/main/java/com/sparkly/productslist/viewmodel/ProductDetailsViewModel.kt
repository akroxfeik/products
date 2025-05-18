package com.sparkly.productslist.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sparkly.productslist.data.model.Product
import com.sparkly.productslist.data.repository.MainRepository
import com.sparkly.productslist.ui.navigation.Arg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val repo: MainRepository
): ViewModel() {
    private val _receivedObject = mutableStateOf<Product?>(null)
    val receivedObject: State<Product?> = _receivedObject
    init {
        stateHandle.get<String>(Arg.JSON)?.let {
            _receivedObject.value = Gson().fromJson(it, Product::class.java)
        }
    }
}