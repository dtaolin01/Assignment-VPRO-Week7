package com.example.poketracker.model

import com.google.gson.annotations.SerializedName

data class CardPrices(
    @SerializedName("market") val market: Double? = null,
    @SerializedName(value = "low", alternate = ["lowPrice"]) val low: Double? = null,
    @SerializedName(value = "listings", alternate = ["activeListings", "totalListings"]) val listings: Int? = null,
    @SerializedName(value = "sellers", alternate = ["verifiedSellers"]) val sellers: Int? = null,
    @SerializedName("primaryPrinting") val primaryPrinting: String? = null,
    @SerializedName("lastUpdated") val lastUpdated: String? = null
)
