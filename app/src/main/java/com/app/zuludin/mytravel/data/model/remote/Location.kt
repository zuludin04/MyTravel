package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("localtime")
    val localtime: String? = null,

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("localtime_epoch")
    val localtime_epoch: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("lon")
    val lon: String? = null,

    @SerializedName("region")
    val region: String? = null,

    @SerializedName("lat")
    val lat: String? = null,

    @SerializedName("tz_id")
    val tz_id: String
)