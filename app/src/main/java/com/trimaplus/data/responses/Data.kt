package com.trimaplus.data.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val id: String,
    val title: String,
    val desc: String,
    val pic: String
) : Parcelable
