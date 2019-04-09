package com.app.zuludin.mytravel.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favourite (
    val id: Long? = null,
    val dataId: String? = null,
    val name: String? = null,
    val thumbnail: String? = null,
    val category: String? = null
) : Parcelable {
    companion object {
        const val FAVORITE_TABLE = "favourite"
        const val ID = "id_"
        const val DATA_ID = "dataId"
        const val NAME = "name"
        const val THUMBNAIL = "thumbnail"
        const val CATEGORY = "category"
    }
}