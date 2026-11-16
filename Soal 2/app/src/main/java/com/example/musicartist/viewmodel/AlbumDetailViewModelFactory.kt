package com.example.musicartist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AlbumDetailViewModelFactory(private val albumId: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailViewModel(albumId) as T
    }
}
