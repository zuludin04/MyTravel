package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    @SerializedName("date")
    val date: String? = null,

    @SerializedName("comment")
    val comment: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("rating")
    val rating: Double? = null
) : Parcelable