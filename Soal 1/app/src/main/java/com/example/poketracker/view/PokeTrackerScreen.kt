package com.example.poketracker.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.poketracker.view.components.AppHeader
import com.example.poketracker.view.components.CardDetailSheet
import com.example.poketracker.view.components.CardGrid
import com.example.poketracker.view.components.ErrorView
import com.example.poketracker.view.components.SearchField
import com.example.poketracker.view.components.SuggestionChips
import com.example.poketracker.view.theme.PokeColors
import com.example.poketracker.viewmodel.CardUiState
import com.example.poketracker.viewmodel.PokeViewModel

@Composable
fun PokeTrackerScreen(viewModel: PokeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()
    val selectedCard by viewModel.selectedCard.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PokeColors.Background)
            .systemBarsPadding()
    ) {
        AppHeader()
        SearchField(
            query = query,
            onQueryChange = viewModel::onQueryChange,
            onSearch = viewModel::search,
            onClear = viewModel::clearQuery,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
        SuggestionChips(onClick = viewModel::onSuggestionClick)
        Spacer(Modifier.height(8.dp))

        Box(Modifier.weight(1f).fillMaxWidth()) {
            when (val state = uiState) {
                is CardUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = PokeColors.Red
                )
                is CardUiState.Error -> ErrorView(
                    title = state.title,
                    message = state.message,
                    onRetry = viewModel::search,
                    modifier = Modifier.align(Alignment.Center)
                )
                is CardUiState.Success -> CardGrid(
                    cards = state.cards,
                    onCardClick = viewModel::selectCard,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    selectedCard?.let { card ->
        CardDetailSheet(card = card, onDismiss = viewModel::dismissCard)
    }
}
