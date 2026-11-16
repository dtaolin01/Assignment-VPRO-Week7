package com.example.musicartist.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val ROUTE_ARTIST = "artist"
private const val ROUTE_ALBUM = "album/{albumId}"

@Composable
fun MusicApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTE_ARTIST) {
        composable(ROUTE_ARTIST) {
            ArtistScreen(
                onAlbumClick = { albumId -> navController.navigate("album/$albumId") }
            )
        }
        composable(
            route = ROUTE_ALBUM,
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) { entry ->
            val albumId = entry.arguments?.getString("albumId").orEmpty()
            AlbumDetailScreen(
                albumId = albumId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
