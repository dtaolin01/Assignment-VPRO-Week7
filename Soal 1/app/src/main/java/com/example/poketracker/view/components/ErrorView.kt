package com.example.poketracker.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.poketracker.view.theme.PokeColors

@Composable
fun ErrorView(
    title: String,
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(PokeColors.SurfaceAlt),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Warning, contentDescription = null, tint = PokeColors.ErrorText)
        }
        Spacer(Modifier.height(12.dp))
        Text(title, color = PokeColors.ErrorText, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(Modifier.height(4.dp))
        Text(message, color = PokeColors.TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = PokeColors.Red, contentColor = Color.White)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(8.dp))
            Text("Try Again", fontWeight = FontWeight.Bold)
        }
    }
}
