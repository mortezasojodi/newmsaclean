package com.example.newcleanmsa.Product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entity.ProductEntity
import com.example.newcleanmsa.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProductFragment : Fragment() {

   private val productViewModel : ProductViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product)
        observe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter= ProductAdapter(this.requireContext(),{ i->
            print(i)
        })
        with(recyclerview) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = productAdapter
        }
        productViewModel.getProduct()
    }
    private fun observe(){
        productViewModel.mState
            .flowWithLifecycle(lifecycle,  Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: ProductActivityState){
        when(state){
            is ProductActivityState.Init -> Unit
            is ProductActivityState.ErrorGet -> {}
            is ProductActivityState.SuccessGet -> handleSuccessLogin(state.productEntity)
            is ProductActivityState.ShowToast -> {}
            is ProductActivityState.IsLoading -> {}
        }
    }

   private fun handleSuccessLogin( productEntity: List<ProductEntity>){

       productAdapter.setData(productEntity)
    }

}
