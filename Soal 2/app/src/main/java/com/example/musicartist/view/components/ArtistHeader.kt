package com.example.musicartist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.musicartist.model.Artist
import com.example.musicartist.view.theme.MusicColors

@Composable
fun ArtistHeader(artist: Artist, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MusicColors.Surface)
    ) {
        AsyncImage(
            model = artist.bannerOrFallback,
            contentDescription = artist.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Gradient gelap agar teks terbaca
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, MusicColors.Background.copy(alpha = 0.95f)),
                        startY = 150f
                    )
                )
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = artist.thumb,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MusicColors.Border)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    text = artist.name ?: "Unknown",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = artist.genre ?: "-",
                        color = MusicColors.Green,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (!artist.country.isNullOrBlank()) {
                        Text(
                            text = "  •  ${artist.country}",
                            color = MusicColors.TextMuted,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}
