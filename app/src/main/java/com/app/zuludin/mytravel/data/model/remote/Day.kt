package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avgvis_km")
    val avgvis_km: String? = null,

    @SerializedName("uv")
    val uv: String? = null,

    @SerializedName("avgtemp_f")
    val avgtemp_f: String? = null,

    @SerializedName("avgtemp_c")
    val avgtemp_c: String? = null,

    @SerializedName("maxtemp_c")
    val maxtemp_c: String? = null,

    @SerializedName("maxtemp_f")
    val maxtemp_f: String? = null,

    @SerializedName("mintemp_c")
    val mintemp_c: String? = null,

    @SerializedName("avgvis_miles")
    val avgvis_miles: String? = null,

    @SerializedName("mintemp_f")
    val mintemp_f: String? = null,

    @SerializedName("totalprecip_in")
    val totalprecip_in: String? = null,

    @SerializedName("avghumidity")
    val avghumidity: String? = null,

    @SerializedName("condition")
    val condition: Condition? = null,

    @SerializedName("maxwind_kph")
    val maxwind_kph: String? = null,

    @SerializedName("maxwind_mph")
    val maxwind_mph: String? = null,

    @SerializedName("totalprecip_mm")
    val totalprecip_mm: String? = null
)