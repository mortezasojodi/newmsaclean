package com.example.data.repository

import com.example.data.api.ProductDetailApi
import com.example.domain.common.base.BaseResult
import com.example.domain.entity.ProductEntity
import com.example.domain.repository.IProductDetailRepository
import com.example.domain.response.ProductResponse
import com.example.domain.usecase.ProductDetailUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(private val productdetailapi : ProductDetailApi) : IProductDetailRepository {
    override suspend fun getProductById(Id: Int): Flow<BaseResult<ProductEntity, ProductResponse>> {
        return  flow {
            val response= productdetailapi.getProductById(Id)
                val body=response.body()!!

            val t = ProductEntity(
                body.id,
                body.title,
                body.price,
                body.category,
                body.description,
                body.image
            )

            emit(BaseResult.Success(t))

        }
    }

}