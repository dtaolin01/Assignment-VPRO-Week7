package com.example.poketracker.model

import com.google.gson.annotations.SerializedName

data class CardsResponse(
    @SerializedName("data") val data: List<PokemonCard>? = null
)
