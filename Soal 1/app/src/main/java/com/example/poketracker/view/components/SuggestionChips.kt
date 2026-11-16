package com.example.poketracker.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.poketracker.view.theme.PokeColors

private val suggestions = listOf("Charizard", "Pikachu", "Mewtwo", "Gengar", "Eevee", "Lugia", "Rayquaza")

@Composable
fun SuggestionChips(onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(suggestions) { name ->
            Text(
                text = name,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(PokeColors.Surface)
                    .clickable { onClick(name) }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            )
        }
    }
}
