package com.example.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class ProductEntity (
    var id: Int,
    var title: String,
    var price: String,
    var category: String,
    var description: String,
    var image: String,
)