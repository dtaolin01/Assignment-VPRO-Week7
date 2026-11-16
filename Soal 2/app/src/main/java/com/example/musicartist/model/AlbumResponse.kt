package com.example.musicartist.model

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("album") val album: List<Album>? = null
)
