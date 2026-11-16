package com.example.poketracker.repository

import com.example.poketracker.model.PokemonCard
import com.example.poketracker.service.PokemonApiService
import com.example.poketracker.service.RetrofitClient

class CardRepository(
    private val service: PokemonApiService = RetrofitClient.service
) {
    // Cache sederhana supaya kuota 100 request/hari tidak cepat habis
    private val cache = mutableMapOf<String, List<PokemonCard>>()

    suspend fun searchCards(query: String): List<PokemonCard> {
        val key = query.trim().lowercase()
        cache[key]?.let { return it }

        val cards = service.searchCards(query = query.trim(), limit = 20).data.orEmpty()
        if (cards.isNotEmpty()) cache[key] = cards
        return cards
    }
}
