package com.vivek.daggerhiltapp



class Repository(private val apiService: ApiService) {
    suspend fun getProductList() = apiService.getProductList()
}