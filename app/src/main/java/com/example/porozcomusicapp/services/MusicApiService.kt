package com.example.porozcomusicapp.services

import com.example.porozcomusicapp.models.Album
import com.example.porozcomusicapp.models.AlbumDetail
import kotlinx.serialization.InternalSerializationApi // Importar la anotación necesaria
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz de Retrofit para consumir la API de música.
 * NOTA: Se aplica @OptIn para permitir el uso de los modelos Album y AlbumDetail
 * marcados con @InternalSerializationApi.
 */
@OptIn(InternalSerializationApi::class) // Aplicar la anotación aquí
interface MusicApiService {

    // Endpoint para obtener la lista completa de álbumes
    @GET("api/albums")
    suspend fun getAlbums(): List<Album>

    // Endpoint para obtener los detalles de un álbum específico por su ID
    @GET("api/albums/{id}")
    suspend fun getAlbumDetail(@Path("id") id: String): AlbumDetail
}
