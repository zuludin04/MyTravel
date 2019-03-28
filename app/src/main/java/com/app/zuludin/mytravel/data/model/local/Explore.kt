package com.app.zuludin.mytravel.data.model.local

data class ExploreItem(
    val image: Int,
    val name: String,
    val category: String
)

data class ExploreList(
    val explores: List<ExploreItem>,
    val title: String
)