package com.example.poketracker.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.poketracker.model.PokemonCard
import com.example.poketracker.util.toUsd
import com.example.poketracker.view.theme.PokeColors

@Composable
fun PokeCardItem(
    card: PokemonCard,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(PokeColors.Surface)
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Box {
            AsyncImage(
                model = card.thumbUrl,
                contentDescription = card.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.72f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(PokeColors.SurfaceAlt)
            )
            if (!card.hp.isNullOrBlank()) {
                Text(
                    text = "${card.hp} HP",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = card.name ?: "Unknown",
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = listOfNotNull(card.setName, card.cardNumber?.let { "#$it" }).joinToString(" • "),
            color = PokeColors.TextMuted,
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "↗ ${card.prices?.market.toUsd()}",
                color = PokeColors.Green,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(PokeColors.Green.copy(alpha = 0.15f))
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            )
            if (!card.rarity.isNullOrBlank()) {
                Text(
                    text = card.rarity,
                    color = PokeColors.TextMuted,
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(PokeColors.SurfaceAlt)
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                )
            }
        }
    }
}
