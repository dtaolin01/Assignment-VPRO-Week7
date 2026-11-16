package com.example.musicartist.repository

import com.example.musicartist.model.AlbumDetail
import com.example.musicartist.model.ArtistPage
import com.example.musicartist.service.AudioDbService
import com.example.musicartist.service.RetrofitClient
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MusicRepository(
    private val service: AudioDbService = RetrofitClient.service
) {
    // Cache sederhana karena API gratis punya batas request
    private val artistCache = mutableMapOf<String, ArtistPage>()
    private val albumCache = mutableMapOf<String, AlbumDetail>()

    suspend fun getArtistPage(name: String): ArtistPage = coroutineScope {
        val key = name.trim().lowercase()
        artistCache[key]?.let { return@coroutineScope it }

        // Dua request berjalan paralel
        val artistDeferred = async { service.searchArtist(name.trim()).artists?.firstOrNull() }
        val albumsDeferred = async { service.searchAlbums(name.trim()).album.orEmpty() }

        val artist = artistDeferred.await()
            ?: throw DataNotFoundException("Artis \"${name.trim()}\" tidak ditemukan")
        val page = ArtistPage(artist, albumsDeferred.await())
        artistCache[key] = page
        page
    }

    suspend fun getAlbumDetail(albumId: String): AlbumDetail = coroutineScope {
        albumCache[albumId]?.let { return@coroutineScope it }

        val albumDeferred = async { service.getAlbum(albumId).album?.firstOrNull() }
        val tracksDeferred = async { service.getTracks(albumId).track.orEmpty() }

        val album = albumDeferred.await()
            ?: throw DataNotFoundException("Detail album tidak ditemukan")
        val detail = AlbumDetail(album, tracksDeferred.await())
        albumCache[albumId] = detail
        detail
    }
}
