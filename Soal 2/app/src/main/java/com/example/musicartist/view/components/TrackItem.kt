package com.example.musicartist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicartist.model.Track
import com.example.musicartist.util.toDuration
import com.example.musicartist.util.twoDigits
import com.example.musicartist.view.theme.MusicColors

@Composable
fun TrackItem(
    index: Int,
    track: Track,
    modifier: Modifier = Modifier
) {
    val number = track.number?.toIntOrNull() ?: (index + 1)

    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = number.twoDigits(),
                color = MusicColors.Green,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(36.dp)
            )
            Column(Modifier.weight(1f)) {
                Text(
                    text = track.name ?: "Unknown",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                if (!track.genre.isNullOrBlank()) {
                    Text(text = track.genre, color = MusicColors.TextMuted, fontSize = 11.sp)
                }
            }
            Text(
                text = track.durationMs.toDuration(),
                color = MusicColors.TextMuted,
                fontSize = 12.sp
            )
        }
        Box(Modifier.fillMaxWidth().height(1.dp).background(MusicColors.Border))
    }
}
