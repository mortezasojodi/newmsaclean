package com.example.data.module

import com.example.data.api.ProductApi
import com.example.data.common.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ProductModule {
    
    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit) : ProductApi {
        return retrofit.create(ProductApi::class.java)
    }
}