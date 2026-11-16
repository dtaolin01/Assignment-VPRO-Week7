package com.example.poketracker.viewmodel

import com.example.poketracker.model.PokemonCard

sealed interface CardUiState {
    object Loading : CardUiState
    data class Success(val cards: List<PokemonCard>, val query: String) : CardUiState
    data class Error(val title: String, val message: String) : CardUiState
}
