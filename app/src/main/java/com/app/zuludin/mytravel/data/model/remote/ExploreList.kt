package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExploreList(
    val explores: List<TravelExplore>,
    val title: String
) : Parcelable