package com.example.domain.usecase

import com.example.domain.common.base.BaseResult
import com.example.domain.entity.ProductEntity
import com.example.domain.repository.IProductDetailRepository
import com.example.domain.response.ProductResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDetailUsecase @Inject constructor(private val productRepositor: IProductDetailRepository) {
    suspend fun execute(productId:Int) : Flow<BaseResult<ProductEntity, ProductResponse>> {
            return  productRepositor.getProductById(productId)
    }
}