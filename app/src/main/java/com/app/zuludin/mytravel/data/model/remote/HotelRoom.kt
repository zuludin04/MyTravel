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
    val image: String? = null,

    @SerializedName("guest")
    val guest: String? = null,

    @SerializedName("bed")
    val bed: String? = null,

    @SerializedName("refundable")
    val refundable: Boolean? = null,

    @SerializedName("breakfast")
    val breakfast: Boolean? = null,

    @SerializedName("wifi")
    val wifi: Boolean? = null
): Parcelable