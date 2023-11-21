package com.example.bookmymovie.models

import android.os.Parcel
import android.os.Parcelable

data class MovieDataItem(
    val __v: Int,
    val _id: String,
    val description: String,
    val genre: List<String>,
    val images: List<String>,
    val language: List<String>,
    val movieType: String,
    val screenTime: String,
    val title: String,
    val videoURL: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString()?: "",
        parcel.createStringArrayList() !!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(__v)
        parcel.writeString(_id)
        parcel.writeString(description)
        parcel.writeStringList(genre)
        parcel.writeStringList(images)
        parcel.writeStringList(language)
        parcel.writeString(movieType)
        parcel.writeString(screenTime)
        parcel.writeString(title)
        parcel.writeString(videoURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDataItem> {
        override fun createFromParcel(parcel: Parcel): MovieDataItem {
            return MovieDataItem(parcel)
        }

        override fun newArray(size: Int): Array<MovieDataItem?> {
            return arrayOfNulls(size)
        }
    }
}