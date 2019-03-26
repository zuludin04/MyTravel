package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.app.zuludin.mytravel.data.model.local.Passenger
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flight(
    var airportOrigin: String? = null,
    var airportDestination: String? = null,
    val flightDuration: String? = null,
    val airlineName: String? = null,
    val airlineIcon: Int? = null,
    var seatClass: String? = null,
    val flightPrice: Int? = null,
    val originTime: String? = null,
    val destinationTime: String? = null,
    var originDate: String? = null,
    var destinationDate: String? = null,
    var originCity: String? = null,
    var destinationCity: String? = null,
    var originCode: String? = null,
    var destinationCode: String? = null,
    val baggage: String? = null,
    val machine: String? = null,
    val seatLayout: String? = null,
    var adultPassenger: Int? = null,
    var childPassenger: Int? = 0,
    var infantPassenger: Int? = 0,
    var totalPrice: String? = null,
    var passengers: List<Passenger>? = null
) : Parcelable