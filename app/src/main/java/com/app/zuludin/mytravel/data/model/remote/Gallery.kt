package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gallery(
    @SerializedName("image")
    val image: String? = null
) : Parcelable