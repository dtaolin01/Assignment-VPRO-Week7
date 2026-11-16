package com.example.musicartist.service

import com.example.musicartist.model.AlbumResponse
import com.example.musicartist.model.ArtistResponse
import com.example.musicartist.model.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AudioDbService {

    @GET("search.php")
    suspend fun searchArtist(@Query("s") artistName: String): ArtistResponse

    @GET("searchalbum.php")
    suspend fun searchAlbums(@Query("s") artistName: String): AlbumResponse

    @GET("album.php")
    suspend fun getAlbum(@Query("m") albumId: String): AlbumResponse

    @GET("track.php")
    suspend fun getTracks(@Query("m") albumId: String): TrackResponse
}
