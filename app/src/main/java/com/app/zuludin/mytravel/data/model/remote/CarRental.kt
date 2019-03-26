package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.app.zuludin.mytravel.data.model.local.Passenger
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarRental(
    val carName: String? = null,
    val rentalName: String? = null,
    var rentalDuration: String? = null,
    var pickupTime: String? = null,
    var rentalLocation: String? = null,
    val rentalPrice: Int? = null,
    val carImage: Int? = null,
    val rentalRating: Double? = null,
    var pickupLocation: String? = null,
    var startDate: String? = null,
    var finishDate: String? = null,
    var totalPrice: String? = null,
    var passenger: Passenger? = null
) : Parcelable