package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.app.zuludin.mytravel.data.model.local.Passenger
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Train(
    var stationOrigin: String? = null,
    var stationDestination: String? = null,
    val trainDuration: String? = null,
    val argoName: String? = null,
    var seatClass: String? = null,
    val trainPrice: Int? = null,
    var cityOrigin: String? = null,
    var cityDestination: String? = null,
    var codeOrigin: String? = null,
    var codeDestination: String? = null,
    val timeOrigin: String? = null,
    val timeDestination: String? = null,
    var departureDate: String? = null,
    var adult: Int? = null,
    var child: Int? = 0,
    var totalPrice: String? = null,
    var passengers: List<Passenger>? = null
): Parcelable