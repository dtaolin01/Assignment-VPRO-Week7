package com.example.musicartist.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicartist.view.theme.MusicColors

@Composable
fun LoadingView(message: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(color = MusicColors.Green, strokeWidth = 3.dp)
        Spacer(Modifier.height(16.dp))
        Text(message, color = MusicColors.TextMuted, fontSize = 12.sp)
    }
}
