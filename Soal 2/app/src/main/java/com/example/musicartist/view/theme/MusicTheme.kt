package com.example.musicartist.view.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MusicTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = MusicColors.Green,
            background = MusicColors.Background,
            surface = MusicColors.Surface,
            onBackground = Color.White,
            onSurface = Color.White
        ),
        content = content
    )
}
