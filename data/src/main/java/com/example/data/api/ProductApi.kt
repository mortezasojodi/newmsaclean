package com.example.data.api

import com.example.domain.response.ProductResponse
import retrofit2.Response
import retrofit2.http.POST

interface ProductApi {
    @POST("products")
    suspend fun getProduct() : Response<ProductResponse>
}

