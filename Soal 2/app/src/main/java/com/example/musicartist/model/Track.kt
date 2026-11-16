package com.example.musicartist.model

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("idTrack") val id: String? = null,
    @SerializedName("strTrack") val name: String? = null,
    @SerializedName("intTrackNumber") val number: String? = null,
    @SerializedName("intDuration") val durationMs: String? = null,
    @SerializedName("strGenre") val genre: String? = null
)
