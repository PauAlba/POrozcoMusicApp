package com.example.porozcomusicapp.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.porozcomusicapp.components.AlbumCardHorizontal
import com.example.porozcomusicapp.components.AlbumCardVertical
import com.example.porozcomusicapp.components.AppBarHome
import com.example.porozcomusicapp.components.MusicPlayerBar
import com.example.porozcomusicapp.services.UiState
import kotlinx.serialization.InternalSerializationApi


@OptIn(InternalSerializationApi::class) //anotacion

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onAlbumClick: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            //  siempre debe estar visible
            MusicPlayerBar(currentAlbum = viewModel.defaultMiniPlayerAlbum)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            AppBarHome(name = "Porozco")

            // main
            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // Sección de Álbumes (LazyRow)
                item {
                    Text(
                        text = "Albums",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )

                    when (val state = viewModel.albumsState) {
                        is UiState.Loading -> {
                            Box(Modifier.fillMaxWidth().height(240.dp)) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        }
                        is UiState.Success -> {
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp)
                            ) {
                                items(state.data) { album ->
                                    AlbumCardHorizontal(album = album, onClick = onAlbumClick)
                                }
                            }
                        }
                        is UiState.Error -> {
                            Text(
                                text = "Error al cargar álbumes: ${state.message}",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }

                // Sección de Reproducido Recientemente (LazyColumn)
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Recently Played",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Text(
                            text = "See more",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                    }
                }

                // La lista vertical de "Recently Played" se mapea a los ítems de LazyColumn
                when (val state = viewModel.recentlyPlayedState) {
                    is UiState.Success -> {
                        items(state.data) { album ->
                            AlbumCardVertical(
                                album = album,
                                onClick = onAlbumClick// padding de la lista
                            )
                        }
                    }
                    is UiState.Loading -> {
                        items(5) { // 5 ítems de carga simulados
                            // Ítem de carga (puedes crear un componente Skeleton)
                            AlbumCardVertical(
                                album = viewModel.defaultMiniPlayerAlbum, // Placeholder
                                onClick = onAlbumClick
                            )
                        }
                    }
                    is UiState.Error -> {
                        item {
                            Text(
                                text = "Error al cargar recientes: ${state.message}",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}