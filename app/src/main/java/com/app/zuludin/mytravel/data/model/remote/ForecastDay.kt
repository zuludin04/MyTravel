package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("date")
    val date: String? = null,

    @SerializedName("astro")
    val astro: Astro? = null,

    @SerializedName("date_epoch")
    val date_epoch: String? = null,

    @SerializedName("day")
    val day: Day? = null
)