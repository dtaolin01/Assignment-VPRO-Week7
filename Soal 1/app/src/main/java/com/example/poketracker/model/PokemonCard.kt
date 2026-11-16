package com.example.poketracker.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class PokemonCard(
    @SerializedName("tcgPlayerId") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("setName") val setName: String? = null,
    @SerializedName("cardNumber") val cardNumber: String? = null,
    @SerializedName("rarity") val rarity: String? = null,
    @SerializedName(value = "cardType", alternate = ["type", "types"]) val typeRaw: JsonElement? = null,
    @SerializedName(value = "artist", alternate = ["illustrator"]) val artist: String? = null,
    @SerializedName("hp") val hp: String? = null,
    @SerializedName("prices") val prices: CardPrices? = null,
    @SerializedName("imageCdnUrl") val imageCdnUrl: String? = null,
    @SerializedName("imageCdnUrl400") val imageCdnUrl400: String? = null,
    @SerializedName("imageCdnUrl800") val imageCdnUrl800: String? = null
) {
    val thumbUrl: String? get() = imageCdnUrl400 ?: imageCdnUrl ?: imageCdnUrl800
    val largeUrl: String? get() = imageCdnUrl800 ?: imageCdnUrl ?: imageCdnUrl400

    // Tipe kartu bisa berupa string atau array, jadi dibaca secara fleksibel
    val displayType: String?
        get() {
            val t = typeRaw ?: return null
            return when {
                t.isJsonNull -> null
                t.isJsonArray -> t.asJsonArray.joinToString(", ") { it.asString }
                t.isJsonPrimitive -> t.asString
                else -> null
            }
        }
}
