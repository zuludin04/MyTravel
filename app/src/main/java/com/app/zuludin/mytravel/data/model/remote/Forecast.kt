package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastday: List<ForecastDay>? = null
)