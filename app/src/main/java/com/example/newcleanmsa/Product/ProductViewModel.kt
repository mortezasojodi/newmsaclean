package com.example.newcleanmsa.Product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.base.BaseResult
import com.example.domain.entity.ProductEntity
import com.example.domain.response.ProductResponse
import com.example.domain.usecase.ProductUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(private val productUseCase: ProductUsecase):ViewModel() {
    private val state = MutableStateFlow<ProductActivityState>(ProductActivityState.Init)
    val mState: StateFlow<ProductActivityState> get() = state

    private fun setLoading(){
        state.value = ProductActivityState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = ProductActivityState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = ProductActivityState.ShowToast(message)
    }

    fun getProduct(){
        viewModelScope.launch {
            productUseCase.execute()
                .onStart {
                    setLoading()
                }
                .catch { exception->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect{baseResult->
                    hideLoading()
                    when(baseResult){
                        is BaseResult.Error-> state.value= ProductActivityState.ErrorGet(baseResult.rawResponse)
                        is BaseResult.Success-> state.value=ProductActivityState.SuccessGet(baseResult.data)
                    }

                }

        }
    }
}

sealed class ProductActivityState  {
    object Init : ProductActivityState()
    data class IsLoading(val isLoading: Boolean) : ProductActivityState()
    data class ShowToast(val message: String) : ProductActivityState()
    data class SuccessGet(val productEntity: List<ProductEntity>) : ProductActivityState()
    data class ErrorGet(val rawResponse: List<ProductResponse>) : ProductActivityState()
}