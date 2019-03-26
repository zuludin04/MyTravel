package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class ForecastWeather(
    @SerializedName("current")
    val current: Current? = null,

    @SerializedName("location")
    val location: Location? = null,

    @SerializedName("forecast")
    val forecast: Forecast? = null
)