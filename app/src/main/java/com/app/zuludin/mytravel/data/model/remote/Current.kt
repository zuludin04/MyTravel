package com.app.zuludin.mytravel.data.model.remote

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("feelslike_c")
    val feelslike_c: String? = null,

    @SerializedName("uv")
    val uv: String? = null,

    @SerializedName("last_updated")
    val last_updated: String? = null,

    @SerializedName("feelslike_f")
    val feelslike_f: String? = null,

    @SerializedName("wind_degree")
    val wind_degree: String? = null,

    @SerializedName("last_updated_epoch")
    val last_update_epoch: String? = null,

    @SerializedName("is_day")
    val is_day: String? = null,

    @SerializedName("precip_in")
    val precip_in: String? = null,

    @SerializedName("wind_dir")
    val wind_dir: String? = null,

    @SerializedName("temp_c")
    val temp_c: String? = null,

    @SerializedName("pressure_in")
    val pressure_in: String? = null,

    @SerializedName("temp_f")
    val temp_f: String? = null,

    @SerializedName("precip_mm")
    val precip_mm: String? = null,

    @SerializedName("cloud")
    val cloud: String? = null,

    @SerializedName("wind_kph")
    val wind_kph: String? = null,

    @SerializedName("condition")
    val condition: Condition? = null,

    @SerializedName("wind_mph")
    val wind_mph: String? = null,

    @SerializedName("vis_km")
    val vis_km: String? = null,

    @SerializedName("humidity")
    val humidity: String? = null,

    @SerializedName("pressure_mb")
    val pressure_mb: String? = null,

    @SerializedName("vis_miles")
    val vis_miles: String? = null
)