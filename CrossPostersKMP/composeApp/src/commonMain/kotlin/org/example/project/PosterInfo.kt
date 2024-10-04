package com.example.crosspostersandroid

import com.example.example.Poster
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PosterInfo (
    @SerialName("poster"          ) var poster          : Poster?              = Poster()

)