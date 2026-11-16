package com.example.musicartist.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicartist.view.theme.MusicColors

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    collapsedLines: Int = 3
) {
    var expanded by rememberSaveable(text) { mutableStateOf(false) }

    Column(modifier) {
        Text(
            text = text,
            color = MusicColors.TextMuted,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedLines,
            overflow = TextOverflow.Ellipsis
        )
        if (text.length > 160) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = if (expanded) "Tutup ringkasan" else "Baca selengkapnya",
                color = MusicColors.Green,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { expanded = !expanded }
            )
        }
    }
}
