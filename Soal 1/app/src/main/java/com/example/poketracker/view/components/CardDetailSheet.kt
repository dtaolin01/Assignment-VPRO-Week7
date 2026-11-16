package com.example.poketracker.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.poketracker.model.PokemonCard
import com.example.poketracker.util.orDash
import com.example.poketracker.util.toUsd
import com.example.poketracker.view.theme.PokeColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailSheet(card: PokemonCard, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val closeSheet: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismiss()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = PokeColors.Background,
        contentColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle(color = PokeColors.TextMuted) }
    ) {
        Box(Modifier.fillMaxWidth()) {
            // Seluruh isi detail bisa di-scroll
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(PokeColors.RedDark, PokeColors.Background),
                            endY = 900f
                        )
                    )
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = card.largeUrl,
                    contentDescription = card.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(260.dp)
                        .aspectRatio(0.72f)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = card.name ?: "Unknown",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(card.setName.orDash(), color = PokeColors.TextMuted, fontSize = 13.sp)
                Spacer(Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    PriceTile(
                        label = "Market Price",
                        value = card.prices?.market.toUsd(),
                        valueColor = PokeColors.Green,
                        modifier = Modifier.weight(1f)
                    )
                    PriceTile(
                        label = "Lowest Price",
                        value = card.prices?.low.toUsd(),
                        valueColor = PokeColors.Yellow,
                        modifier = Modifier.weight(1f)
                    )
                }

                SectionTitle("Market Activity")
                InfoGroup(
                    listOf(
                        "Active Listings" to card.prices?.listings.orDash(),
                        "Verified Sellers" to card.prices?.sellers.orDash(),
                        "Primary Printing" to card.prices?.primaryPrinting.orDash()
                    )
                )

                SectionTitle("Card Specifications")
                InfoGroup(
                    listOf(
                        "Card Number" to card.cardNumber.orDash(),
                        "Rarity" to card.rarity.orDash(),
                        "Type" to card.displayType.orDash(),
                        "Illustrator" to card.artist.orDash()
                    )
                )

                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = closeSheet,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PokeColors.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Done", fontWeight = FontWeight.Bold)
                }
            }

            IconButton(
                onClick = closeSheet,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Tutup", tint = Color.White)
            }
        }
    }
}
