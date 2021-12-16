package com.nasa.lab4

import android.net.Uri

data class AudioFile(
    val title: String,
    val artist: String,
    val duration: Int,
    val uri: Uri
)