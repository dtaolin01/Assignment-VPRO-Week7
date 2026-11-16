package com.example.musicartist.model

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("artists") val artists: List<Artist>? = null
)
