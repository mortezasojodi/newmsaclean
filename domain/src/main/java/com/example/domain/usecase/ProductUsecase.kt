package com.example.domain.usecase

import com.example.domain.common.base.BaseResult
import com.example.domain.entity.ProductEntity
import com.example.domain.repository.IProductRepository
import com.example.domain.response.ProductResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductUsecase @Inject constructor(private val productRepository: IProductRepository) {

  suspend fun execute(): Flow<BaseResult<List<ProductEntity>, List<ProductResponse>>> {
         return productRepository.getProduct();
    }
}