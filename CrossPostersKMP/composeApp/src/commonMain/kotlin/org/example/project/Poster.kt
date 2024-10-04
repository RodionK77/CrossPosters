package com.example.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Poster (

  @SerialName("url"        ) var url        : String? = null,
  @SerialName("previewUrl" ) var previewUrl : String? = null

)