package com.example.musicartist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.musicartist.model.AlbumDetail
import com.example.musicartist.view.components.*
import com.example.musicartist.view.theme.MusicColors
import com.example.musicartist.viewmodel.AlbumDetailUiState
import com.example.musicartist.viewmodel.AlbumDetailViewModel
import com.example.musicartist.viewmodel.AlbumDetailViewModelFactory

@Composable
fun AlbumDetailScreen(
    albumId: String,
    onBack: () -> Unit,
    viewModel: AlbumDetailViewModel = viewModel(factory = AlbumDetailViewModelFactory(albumId))
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MusicColors.Background)
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Kembali",
                tint = Color.White,
                modifier = Modifier.clickable(onClick = onBack)
            )
            Spacer(Modifier.width(16.dp))
            Text("Detail Album", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Box(Modifier.weight(1f).fillMaxWidth()) {
            when (val state = uiState) {
                is AlbumDetailUiState.Loading -> LoadingView(
                    message = "Memuat detail album & track list...",
                    modifier = Modifier.align(Alignment.Center)
                )
                is AlbumDetailUiState.Error -> ErrorView(
                    message = state.message,
                    onRetry = viewModel::load,
                    modifier = Modifier.align(Alignment.Center)
                )
                is AlbumDetailUiState.Success -> AlbumDetailContent(state.detail)
            }
        }
    }
}

@Composable
private fun AlbumDetailContent(detail: AlbumDetail) {
    val album = detail.album

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = album.thumb,
                    contentDescription = album.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MusicColors.Surface)
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = album.name ?: "Unknown",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = album.artist ?: "-",
                    color = MusicColors.Green,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoChip("Tahun ${album.year ?: "-"}")
                    InfoChip(album.genre ?: "-")
                }
            }
        }

        item {
            Column(Modifier.padding(top = 24.dp)) {
                Text("Deskripsi Album", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                ExpandableText(text = album.description ?: "Deskripsi tidak tersedia.")
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("♪", color = MusicColors.Green, fontSize = 18.sp)
                    Spacer(Modifier.width(8.dp))
                    Text("Daftar Lagu", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                CountBadge("${detail.tracks.size} Lagu")
            }
        }

        if (detail.tracks.isEmpty()) {
            item {
                Text(
                    "Daftar lagu belum tersedia.",
                    color = MusicColors.TextMuted,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        itemsIndexed(detail.tracks, key = { index, track -> "${track.id}_$index" }) { index, track ->
            TrackItem(index = index, track = track)
        }
    }
}
