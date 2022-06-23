package com.vivek.daggerhiltapp

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProductList(): Response<ProductList>

}