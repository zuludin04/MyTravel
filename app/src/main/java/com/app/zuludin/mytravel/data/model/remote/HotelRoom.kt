package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelRoom(
    @SerializedName("hotel")
    val hotel: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("price")
    val price: Int? = 0,

    @SerializedName("image")
    val image: String? = null
): Parcelable