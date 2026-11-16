package com.example.musicartist.model

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("track") val track: List<Track>? = null
)
