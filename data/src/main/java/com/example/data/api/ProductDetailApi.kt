package com.example.data.api

import com.example.domain.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductResponse>
}