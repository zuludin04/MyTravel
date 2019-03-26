package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

class CurrentWeather(

    @SerializedName("current")
    val current: Current? = null,

    @SerializedName("location")
    val location: Location? = null
)