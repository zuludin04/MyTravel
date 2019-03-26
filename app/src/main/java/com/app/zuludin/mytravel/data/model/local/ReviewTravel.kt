package com.app.zuludin.mytravel.data.model.local

data class ReviewTravel(
    val reviewName: String,
    val reviewDate: String,
    val reviewRating: String
)

data class ReviewList(
    val reviewList: List<ReviewTravel>
)