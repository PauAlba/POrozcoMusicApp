package com.example.porozcomusicapp.models

import kotlinx.serialization.Serializable
// Importación de la anotación específica que el compilador sugiere.
import kotlinx.serialization.InternalSerializationApi

/**
 * Modelo de datos simple para un álbum en la lista de Home.
 * El campo 'id' se usará para navegar a la pantalla de detalle.
 */
// Aplicar la anotación directamente a la clase para eliminar el error.
@InternalSerializationApi
@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val imageUrl: String
)

/**
 * Modelo de datos completo para el detalle de un álbum.
 */
// Aplicar la anotación también al segundo modelo.
@InternalSerializationApi
@Serializable
data class AlbumDetail(
    val id: String,
    val title: String,
    val artist: String,
    val imageUrl: String,
    val description: String // Descripción detallada del álbum
)