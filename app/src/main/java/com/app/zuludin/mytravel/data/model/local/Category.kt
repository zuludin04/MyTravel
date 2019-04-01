package com.app.zuludin.mytravel.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryItem(
    val image: Int,
    val item: String,
    val total: String
) : Parcelable

data class CategoryList(
    val items: List<CategoryItem>,
    val title: String
)