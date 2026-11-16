package com.example.musicartist.model

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("idArtist") val id: String? = null,
    @SerializedName("strArtist") val name: String? = null,
    @SerializedName("strGenre") val genre: String? = null,
    @SerializedName("strCountry") val country: String? = null,
    @SerializedName("strArtistThumb") val thumb: String? = null,
    @SerializedName("strArtistBanner") val banner: String? = null,
    @SerializedName("strArtistFanart") val fanart: String? = null,
    @SerializedName("strBiographyEN") val biography: String? = null
) {
    val bannerOrFallback: String? get() = banner ?: fanart ?: thumb
}
