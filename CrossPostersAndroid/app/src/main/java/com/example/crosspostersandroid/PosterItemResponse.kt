package com.example.crosspostersandroid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PosterItemResponse(

    @SerialName("docs")
    val docs: ArrayList<PosterInfo> = arrayListOf(),
)