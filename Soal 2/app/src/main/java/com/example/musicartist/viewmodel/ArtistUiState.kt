package com.example.musicartist.viewmodel

import com.example.musicartist.model.ArtistPage

sealed interface ArtistUiState {
    object Loading : ArtistUiState
    data class Success(val page: ArtistPage) : ArtistUiState
    data class Error(val message: String) : ArtistUiState
}
