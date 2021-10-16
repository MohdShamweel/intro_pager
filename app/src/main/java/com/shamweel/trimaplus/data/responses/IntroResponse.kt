package com.shamweel.trimaplus.data.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class IntroResponse(
    val data: Data,
    val success: Boolean
) : Parcelable
