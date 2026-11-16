package com.example.musicartist.viewmodel

import com.example.musicartist.model.AlbumDetail

sealed interface AlbumDetailUiState {
    object Loading : AlbumDetailUiState
    data class Success(val detail: AlbumDetail) : AlbumDetailUiState
    data class Error(val message: String) : AlbumDetailUiState
}
