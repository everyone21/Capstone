package com.example.capstone

import android.os.Parcel
import android.os.Parcelable

data class Report(
    val id: String,
    val title: String,
    val description: String,
    val mediaURL: String?,
    val date: String
)