package com.example.newcleanmsa.Product

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.ProductEntity
import com.example.newcleanmsa.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class ProductFragment : Fragment() {

   private val productViewModel : ProductViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var  la: RecyclerView.LayoutManager;
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
        productAdapter= ProductAdapter(this.requireContext(),{ it->
            val b = bundleOf("product_id" to it.id)
            findNavController().navigate(R.id.action_home_to_detail,b)
        })

        with(recyclerview) {
            layoutManager = GridLayoutManager(requireContext(), 2)
                la=recyclerview.layoutManager!!
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
            is ProductActivityState.IsLoading -> {
                loading.visibility= if(state.isLoading)View.VISIBLE else View.GONE
            }
        }
    }

   private fun handleSuccessLogin( productEntity: List<ProductEntity>){

       productAdapter.setData(productEntity)
    }



}
