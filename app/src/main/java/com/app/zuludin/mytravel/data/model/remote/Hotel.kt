package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hotel(
    val hotelImage: Int? = null,
    val hotelName: String? = null,
    var hotelPrice: Int? = null,
    val hotelRating: Double? = null,
    var hotelLocation: String? = null,
    var hotelCity: String? = null,
    var checkIn: String? = null,
    var checkOut: String? = null,
    var stayDuration: Int? = null,
    val timeIn: String? = null,
    val timeOut: String? = null,
    var totalGuest: String? = null,
    var totalRoom: String? = null,
    var roomSelected: HotelRoom? = null,
    var totalPrice: String? = null,
    val rooms: ArrayList<HotelRoom>? = null
): Parcelable