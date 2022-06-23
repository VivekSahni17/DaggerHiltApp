package com.vivek.daggerhiltapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val productListLiveData = MutableLiveData<List<ProductList>?>()

    fun getProduct() = productListLiveData

    init {
        loadProductList()
    }

    private fun loadProductList() {
        viewModelScope.launch {
            val product = repository.getProductList()
            when (product.isSuccessful) {
                true -> {
                    product.body()?.let {
                        with(it.Product) {
                            val productList = listOf<ProductList>()
//                            forEach { (_, _, _, _, _, _, thumbnail, _, _, _, _, _, _, _, brand) ->
//                                productList = productList + ProductList(thumbnail, brand)
//                            }
                            productListLiveData.postValue(productList)
                        }
                    }
                }
                else -> {
                    //Timber.e(productListLiveData.message())

                }
            }
        }
    }
}