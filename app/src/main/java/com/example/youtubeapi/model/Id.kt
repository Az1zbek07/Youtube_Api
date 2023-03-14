package com.example.youtubeapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Id(
    val kind: String,
    val videoId: String
): Parcelable