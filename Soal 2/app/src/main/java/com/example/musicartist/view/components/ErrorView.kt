package com.example.musicartist.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicartist.view.theme.MusicColors

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(48.dp).border(2.dp, MusicColors.Red, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("!", color = MusicColors.Red, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp))
        Text("Terjadi Kendala", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(message, color = MusicColors.TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = MusicColors.Green,
                contentColor = Color.Black
            )
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(8.dp))
            Text("Coba Lagi", fontWeight = FontWeight.Bold)
        }
    }
}
