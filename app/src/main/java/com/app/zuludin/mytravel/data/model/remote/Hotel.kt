package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hotel(
    @SerializedName("dataId")
    val dataId: Int? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("startFrom")
    val startFrom: Int? = null,

    @SerializedName("rating")
    val rating: Double? = null,

    @SerializedName("region")
    val location: String? = null,

    @SerializedName("timeIn")
    val timeIn: String? = null,

    @SerializedName("timeOut")
    val timeOut: String? = null,

    @SerializedName("rooms")
    val rooms: ArrayList<HotelRoom>? = null,

    @SerializedName("review")
    val review: List<Review>? = null,

    @SerializedName("facilities")
    val facilities: List<Facility>? = null,

    var city: String? = null,
    var checkIn: String? = null,
    var checkOut: String? = null,
    var duration: Int? = null,
    var guest: String? = null,
    var room: String? = null
): Parcelable

data class HotelList (
    @SerializedName("hotel")
    val hotels: List<Hotel>
)