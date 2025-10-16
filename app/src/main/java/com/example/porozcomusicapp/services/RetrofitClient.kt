package com.example.porozcomusicapp.services

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Cliente Singleton para Retrofit.
 */
object RetrofitClient {

    private const val BASE_URL = "https://music.juanfrausto.com/"
    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    // 1. Configuración del Builder de Retrofit.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Usar kotlinx.serialization como convertidor
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    // 2. Servicio de API lazy-loaded.
    val musicApiService: MusicApiService by lazy {
        retrofit.create(MusicApiService::class.java)
    }
}
