package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class Astro(
    @SerializedName("moonset")
    val moonset: String? = null,

    @SerializedName("sunrise")
    val sunrise: String? = null,

    @SerializedName("sunset")
    val sunset: String? = null,

    @SerializedName("moonrise")
    val moonrise: String? = null
)