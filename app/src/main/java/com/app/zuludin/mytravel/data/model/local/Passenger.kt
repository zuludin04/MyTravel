package com.app.zuludin.mytravel.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Passenger(
    var name: String,
    val type: String
): Parcelable