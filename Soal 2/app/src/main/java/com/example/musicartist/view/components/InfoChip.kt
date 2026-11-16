package com.example.musicartist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicartist.view.theme.MusicColors

@Composable
fun InfoChip(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color(0xFFCBD0D6),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(MusicColors.Surface)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}
