package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Facility (
    @SerializedName("icon")
    val icon: String? = null,

    @SerializedName("facility")
    val facility: String? = null
) : Parcelable