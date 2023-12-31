package com.example.bookmymovie.models

import android.os.Parcel
import android.os.Parcelable

data class TheatreDetails(
    val address: String,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TheatreDetails> {
        override fun createFromParcel(parcel: Parcel): TheatreDetails {
            return TheatreDetails(parcel)
        }

        override fun newArray(size: Int): Array<TheatreDetails?> {
            return arrayOfNulls(size)
        }
    }
}