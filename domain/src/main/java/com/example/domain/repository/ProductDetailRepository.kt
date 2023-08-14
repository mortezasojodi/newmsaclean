package com.example.domain.repository

import com.example.domain.common.base.BaseResult
import com.example.domain.entity.ProductEntity
import com.example.domain.response.ProductResponse
import kotlinx.coroutines.flow.Flow

interface IProductDetailRepository {
   suspend fun getProductById(Id:Int): Flow<BaseResult<ProductEntity, ProductResponse>>
}