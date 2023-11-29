package com.example.capstone

import android.os.Parcel
import android.os.Parcelable

data class Report(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val mediaURL: String? = null,
    val date: String? = null
)