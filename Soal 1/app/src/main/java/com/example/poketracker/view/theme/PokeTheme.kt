package com.example.poketracker.view.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PokeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = PokeColors.Red,
            background = PokeColors.Background,
            surface = PokeColors.Surface,
            onBackground = Color.White,
            onSurface = Color.White
        ),
        content = content
    )
}
