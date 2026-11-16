package com.example.musicartist.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("idAlbum") val id: String? = null,
    @SerializedName("strAlbum") val name: String? = null,
    @SerializedName("strArtist") val artist: String? = null,
    @SerializedName("intYearReleased") val year: String? = null,
    @SerializedName("strGenre") val genre: String? = null,
    @SerializedName("strAlbumThumb") val thumb: String? = null,
    @SerializedName("strDescriptionEN") val description: String? = null
)
