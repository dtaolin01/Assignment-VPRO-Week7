package com.example.poketracker.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.poketracker.view.theme.PokeColors

@Composable
fun AppHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(PokeColors.Red),
            contentAlignment = Alignment.Center
        ) {
            Box(Modifier.size(16.dp).border(3.dp, Color.White, CircleShape))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text("PoClaymon", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Real-time TCG Card Valuation", color = PokeColors.TextMuted, fontSize = 11.sp)
        }
        Text(
            text = "● LIVE API",
            color = PokeColors.Green,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(PokeColors.Green.copy(alpha = 0.15f))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}
