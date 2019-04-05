package com.app.zuludin.mytravel.data.model.remote

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Transaction(
    var id: String? = null,
    var book: String? = null,
    var city: String? = null,
    var price: String? = null,
    var service: String? = null,
    var date: String? = null,
    var duration: String? = null,
    var method: String? = null,
    var type: String? = null,
    var adult: Int? = null,
    var child: Int? = null,
    var infant: Int? = null,
    val status: String? = "Process"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    @Exclude
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(book)
        parcel.writeString(city)
        parcel.writeString(price)
        parcel.writeString(service)
        parcel.writeString(date)
        parcel.writeString(duration)
        parcel.writeString(method)
        parcel.writeString(type)
        parcel.writeValue(adult)
        parcel.writeValue(child)
        parcel.writeValue(infant)
        parcel.writeString(status)
    }

    @Exclude
    override fun describeContents(): Int {
        return 0
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "book" to book,
            "city" to city,
            "startFrom" to price,
            "service" to service,
            "date" to date,
            "duration" to duration,
            "method" to method,
            "type" to type,
            "adult" to adult,
            "child" to child,
            "infant" to infant,
            "status" to status
        )
    }

    companion object CREATOR : Parcelable.Creator<Transaction> {
        override fun createFromParcel(parcel: Parcel): Transaction {
            return Transaction(parcel)
        }

        override fun newArray(size: Int): Array<Transaction?> {
            return arrayOfNulls(size)
        }
    }
}