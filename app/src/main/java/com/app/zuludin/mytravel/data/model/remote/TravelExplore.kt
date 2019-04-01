package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TravelExplore(
    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("review")
    val review: List<Review>? = null,

    @SerializedName("name")
    val name: String? = null,


    @SerializedName("rating")
    val rating: Double? = null,

    @SerializedName("about")
    val about: String? = null,

    @SerializedName("location")
    val location: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("open")
    val open: String? = null,

    @SerializedName("gallery")
    val gallery: List<Gallery>? = null
) : Parcelable