package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelRoom(
    val hotel: String? = null,
    val roomType: String? = null,
    val roomPrice: Int? = 0
): Parcelable