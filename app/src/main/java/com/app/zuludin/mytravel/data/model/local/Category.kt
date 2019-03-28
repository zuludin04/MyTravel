package com.app.zuludin.mytravel.data.model.local

data class CategoryItem(
    val image: Int,
    val item: String,
    val total: String
)

data class CategoryList(
    val items: List<CategoryItem>,
    val title: String
)