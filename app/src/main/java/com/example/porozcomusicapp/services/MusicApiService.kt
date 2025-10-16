package com.example.porozcomusicapp.services

import com.example.porozcomusicapp.models.Album
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

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumDetail(@Path("id") id: String): Album
}
