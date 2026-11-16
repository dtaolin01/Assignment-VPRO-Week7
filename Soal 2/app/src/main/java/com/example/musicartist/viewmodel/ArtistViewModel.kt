package com.example.musicartist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicartist.repository.MusicRepository
import com.example.musicartist.util.toUserMessage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtistViewModel(
    private val repository: MusicRepository = MusicRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<ArtistUiState>(ArtistUiState.Loading)
    val uiState: StateFlow<ArtistUiState> = _uiState.asStateFlow()

    private val _query = MutableStateFlow("Coldplay")
    val query: StateFlow<String> = _query.asStateFlow()

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

    fun search() {
        val keyword = _query.value.trim()
        searchJob?.cancel()

        if (keyword.isEmpty()) {
            _uiState.value = ArtistUiState.Error("Ketik nama artis yang ingin dicari")
            return
        }

        searchJob = viewModelScope.launch {
            _uiState.value = ArtistUiState.Loading
            _uiState.value = try {
                ArtistUiState.Success(repository.getArtistPage(keyword))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                ArtistUiState.Error(e.toUserMessage())
            }
        }
    }
}
