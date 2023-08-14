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
    override suspend fun getProduct(): Flow<BaseResult<List<ProductEntity>, List<ProductResponse>>> {
        return flow {
            val response = productApi.getProduct()
            if(response.isSuccessful){
                val listbody = response.body()!!
                 val loginEntity= ArrayList<ProductEntity>()

                for ( body in listbody) {

                    val t = ProductEntity(
                        body.id,
                        body.title,
                        body.price,
                        body.category,
                        body.description,
                        body.image
                    )
//                    val myArrayList = ArrayList<String>()
//                    myArrayList.add("Tom Hanks")
                    loginEntity.add(t)
                }
                emit(BaseResult.Success(loginEntity))
            } else{
//                val type = object : TypeToken<WrappedResponse<LoginResponse>>(){}.type
//                val err : WrappedResponse<LoginResponse> = Gson().fromJson(response.errorBody()!!.charStream(), type)
//                err.code = response.code()
//                emit(BaseResult.Error(err))
            }
        }
    }
}