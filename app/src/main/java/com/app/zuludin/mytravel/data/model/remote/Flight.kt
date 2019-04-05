package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.app.zuludin.mytravel.data.model.local.Passenger
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flight(
    @SerializedName("dataId")
    val dataId: Int? = null,

    @SerializedName("departureTime")
    val departureTime: String? = null,

    @SerializedName("arrivalTime")
    val arrivalTime: String? = null,

    @SerializedName("icon")
    val icon: String? = null,

    @SerializedName("price")
    val price: Int? = null,

    @SerializedName("machine")
    val machine: String? = null,

    @SerializedName("cabin")
    val cabin: String? = null,

    @SerializedName("duration")
    val duration: String? = null,

    @SerializedName("airline")
    val airline: String? = null,

    @SerializedName("seatLayout")
    val seatLayout: String? = null,

    var seatClass: String? = null,
    var airportDestination: String? = null,
    var airportOrigin: String? = null,
    var date: String? = null,
    var originCity: String? = null,
    var destinationCity: String? = null,
    var originCode: String? = null,
    var destinationCode: String? = null,
    var adult: Int? = null,
    var child: Int? = null,
    var infant: Int? = null
) : Parcelable

data class FlightList(
    @SerializedName("flight")
    val flights: List<Flight>
)