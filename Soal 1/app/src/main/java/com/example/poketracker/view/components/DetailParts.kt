package com.example.poketracker.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun PriceTile(label: String, value: String, valueColor: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(PokeColors.Surface)
            .border(1.dp, PokeColors.Border, RoundedCornerShape(14.dp))
            .padding(12.dp)
    ) {
        Text(label, color = PokeColors.TextMuted, fontSize = 11.sp)
        Spacer(Modifier.height(6.dp))
        Text(value, color = valueColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.fillMaxWidth().padding(top = 20.dp, bottom = 8.dp)
    )
}

@Composable
fun InfoGroup(rows: List<Pair<String, String>>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(PokeColors.Surface)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        rows.forEach { (label, value) ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(label, color = PokeColors.TextMuted, fontSize = 13.sp)
                Text(
                    value,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
