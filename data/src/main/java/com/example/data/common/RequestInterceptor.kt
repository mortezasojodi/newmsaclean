package com.example.data.common

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()

            .build()
        return chain.proceed(newRequest)
    }
}