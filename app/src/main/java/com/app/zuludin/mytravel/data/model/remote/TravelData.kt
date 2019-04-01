package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class TravelData(
    @SerializedName("explore")
    val explore: List<TravelExplore>
)