package com.nasa.lab3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Text(
    val text: String,
    val font: String,
) : Parcelable