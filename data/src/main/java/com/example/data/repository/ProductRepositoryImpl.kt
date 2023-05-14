package com.example.data.repository

import com.example.data.api.ProductApi
import com.example.domain.common.base.BaseResult
import com.example.domain.entity.ProductEntity
import com.example.domain.repository.IProductRepository
import com.example.domain.response.ProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(private val productApi: ProductApi) : IProductRepository {
    override suspend fun getProduct(): Flow<BaseResult<ProductEntity, ProductResponse>> {
        return flow {
            val response = productApi.getProduct()
            if(response.isSuccessful){
                val body = response.body()!!
//                val loginEntity = LoginEntity(body.data?.id!!, body.data?.name!!, body.data?.email!!, body.data?.token!!)
//                emit(BaseResult.Success(loginEntity))
            } else{
//                val type = object : TypeToken<WrappedResponse<LoginResponse>>(){}.type
//                val err : WrappedResponse<LoginResponse> = Gson().fromJson(response.errorBody()!!.charStream(), type)
//                err.code = response.code()
//                emit(BaseResult.Error(err))
            }
        }
    }
}