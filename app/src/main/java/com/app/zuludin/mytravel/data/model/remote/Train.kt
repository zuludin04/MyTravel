package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.app.zuludin.mytravel.data.model.local.Passenger
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Train(
    @SerializedName("dataId")
    val dataId: Int? = null,

    @SerializedName("duration")
    val duration: String? = null,

    @SerializedName("argo")
    val argo: String? = null,

    @SerializedName("price")
    val price: Int? = null,

    @SerializedName("timeOrigin")
    val timeOrigin: String? = null,

    @SerializedName("timeDestination")
    val timeDestination: String? = null,
    
    var stationOrigin: String? = null,
    var stationDestination: String? = null,
    var seatClass: String? = null,
    var cityOrigin: String? = null,
    var cityDestination: String? = null,
    var codeOrigin: String? = null,
    var codeDestination: String? = null,
    var date: String? = null,
    var adult: Int? = null,
    var child: Int? = null
): Parcelable

data class TrainList(
    @SerializedName("train")
    val trains: List<Train>
)