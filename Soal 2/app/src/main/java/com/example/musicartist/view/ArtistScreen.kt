package com.example.musicartist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicartist.model.ArtistPage
import com.example.musicartist.view.components.*
import com.example.musicartist.view.theme.MusicColors
import com.example.musicartist.viewmodel.ArtistUiState
import com.example.musicartist.viewmodel.ArtistViewModel

@Composable
fun ArtistScreen(
    onAlbumClick: (String) -> Unit,
    viewModel: ArtistViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MusicColors.Background)
            .systemBarsPadding()
    ) {
        AppTitle()
        SearchField(
            query = query,
            onQueryChange = viewModel::onQueryChange,
            onSearch = viewModel::search,
            onClear = viewModel::clearQuery,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))

        Box(Modifier.weight(1f).fillMaxWidth()) {
            when (val state = uiState) {
                is ArtistUiState.Loading -> LoadingView(
                    message = "Mencari artis…",
                    modifier = Modifier.align(Alignment.Center)
                )
                is ArtistUiState.Error -> ErrorView(
                    message = state.message,
                    onRetry = viewModel::search,
                    modifier = Modifier.align(Alignment.Center)
                )
                is ArtistUiState.Success -> ArtistContent(
                    page = state.page,
                    onAlbumClick = onAlbumClick
                )
            }
        }
    }
}

@Composable
private fun ArtistContent(
    page: ArtistPage,
    onAlbumClick: (String) -> Unit
) {
    // Satu grid yang bisa di-scroll: banner, bio, judul diskografi, lalu album 2 kolom
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            ArtistHeader(artist = page.artist)
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text("Tentang Artis", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                ExpandableText(text = page.artist.biography ?: "Biografi tidak tersedia.")
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Diskografi Album", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Koleksi rilisan resmi", color = MusicColors.TextMuted, fontSize = 11.sp)
                }
                CountBadge("${page.albums.size} Album")
            }
        }

        if (page.albums.isEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text("Belum ada data album.", color = MusicColors.TextMuted, fontSize = 12.sp)
            }
        }

        itemsIndexed(page.albums, key = { index, album -> "${album.id}_$index" }) { _, album ->
            AlbumItem(
                album = album,
                onClick = { album.id?.let(onAlbumClick) }
            )
        }
    }
}
