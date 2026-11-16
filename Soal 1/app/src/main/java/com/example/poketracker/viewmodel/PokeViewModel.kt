package com.example.poketracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poketracker.model.PokemonCard
import com.example.poketracker.repository.CardRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PokeViewModel(
    private val repository: CardRepository = CardRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<CardUiState>(CardUiState.Loading)
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    private val _query = MutableStateFlow("Charizard")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _selectedCard = MutableStateFlow<PokemonCard?>(null)
    val selectedCard: StateFlow<PokemonCard?> = _selectedCard.asStateFlow()

    private var searchJob: Job? = null

    init {
        search()
    }

    fun onQueryChange(value: String) {
        _query.value = value
    }

    fun clearQuery() {
        _query.value = ""
    }

    fun onSuggestionClick(name: String) {
        _query.value = name
        search()
    }

    fun selectCard(card: PokemonCard) {
        _selectedCard.value = card
    }

    fun dismissCard() {
        _selectedCard.value = null
    }

    fun search() {
        val keyword = _query.value.trim()
        searchJob?.cancel()

        if (keyword.isEmpty()) {
            _uiState.value = CardUiState.Error(
                title = "Masukkan Kata Kunci",
                message = "Ketik nama kartu Pokémon yang ingin dicari"
            )
            return
        }

        searchJob = viewModelScope.launch {
            _uiState.value = CardUiState.Loading
            _uiState.value = try {
                val cards = repository.searchCards(keyword)
                if (cards.isEmpty()) {
                    CardUiState.Error("Card Not Found", "Tidak ada kartu untuk \"$keyword\"")
                } else {
                    CardUiState.Success(cards, keyword)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: HttpException) {
                CardUiState.Error("Connection Failed", "HTTP ${e.code()}")
            } catch (e: IOException) {
                CardUiState.Error("Connection Failed", "Periksa koneksi internet kamu")
            } catch (e: Exception) {
                CardUiState.Error("Something Went Wrong", e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
