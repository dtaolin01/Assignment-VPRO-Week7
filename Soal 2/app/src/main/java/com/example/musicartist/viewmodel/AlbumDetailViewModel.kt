package com.example.musicartist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicartist.repository.MusicRepository
import com.example.musicartist.util.toUserMessage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumDetailViewModel(
    private val albumId: String,
    private val repository: MusicRepository = MusicRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AlbumDetailUiState>(AlbumDetailUiState.Loading)
    val uiState: StateFlow<AlbumDetailUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.value = AlbumDetailUiState.Loading
            _uiState.value = try {
                AlbumDetailUiState.Success(repository.getAlbumDetail(albumId))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                AlbumDetailUiState.Error(e.toUserMessage())
            }
        }
    }
}
