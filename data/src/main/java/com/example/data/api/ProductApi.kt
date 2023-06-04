package com.example.data.api

import com.example.domain.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApi {
    @GET("products")
    suspend fun getProduct() : Response<List<ProductResponse>>
}

