package com.app.zuludin.mytravel.data.model.local

import android.os.Parcelable
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExploreItem(
    val image: Int,
    val name: String,
    val category: String
) : Parcelable

data class ExploreList(
    val explores: List<TravelExplore>,
    val title: String
)