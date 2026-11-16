package com.example.poketracker.service

import com.example.poketracker.model.CardsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {
    @GET("cards")
    suspend fun searchCards(
        @Query("search") query: String,
        @Query("limit") limit: Int = 20
    ): CardsResponse
}
