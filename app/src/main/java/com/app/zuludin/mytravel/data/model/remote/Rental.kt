package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.app.zuludin.mytravel.data.model.local.Passenger
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rental(
    @SerializedName("dataId")
    val dataId: Int? = null,

    @SerializedName("car")
    val car: String? = null,

    @SerializedName("rental")
    val rental: String? = null,

    @SerializedName("price")
    val price: Int? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("rating")
    val rating: Double? = null,

    @SerializedName("machine")
    val machine: String? = null,

    @SerializedName("seats")
    val seats: String? = null,

    @SerializedName("review")
    val review: List<Review>? = null,

    var region: String? = null,
    var pickUpTime: String? = null,
    var duration: String? = null,
    var pickupLocation: String? = null,
    var startDate: String? = null,
    var finishDate: String? = null
) : Parcelable

data class RentalList(
    @SerializedName("rental")
    val rentals: List<Rental>
)