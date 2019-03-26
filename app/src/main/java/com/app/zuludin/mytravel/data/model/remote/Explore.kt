package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Explore(
    val image: Int,
    val name: String,
    val activities: String,
    val rating: Float
) : Parcelable