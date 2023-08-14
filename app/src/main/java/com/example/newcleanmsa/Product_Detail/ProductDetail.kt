package com.example.newcleanmsa.Product_Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newcleanmsa.R

class ProductDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detali, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchCurrentProduct()
    }
    private fun fetchCurrentProduct(){
        val id = arguments?.getInt("product_id") ?: 0
        if (id != 0){
        }
    }

}