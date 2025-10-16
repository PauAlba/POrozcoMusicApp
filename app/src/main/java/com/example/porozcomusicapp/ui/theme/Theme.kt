package com.example.porozcomusicapp.ui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// --- 1. ESQUEMAS DE COLORES ---

// Tema Claro (similar al mock: fondo claro, acentos morados/oscuros)
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = Purple80,
    onPrimaryContainer = Color.Black,
    secondary = DeepPurple,
    onSecondary = Color.White,
    background = BackgroundLightGray, // Fondo casi blanco/gris claro para las cards
    onBackground = Color.Black,
    surface = Color.White, // Fondo de las Cards (Recently Played, About)
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White
    /* Otros colores por defecto... */
)

// Tema Oscuro (Para el modo nocturno, si se desea soporte)
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Color.Black,
    primaryContainer = Purple40,
    onPrimaryContainer = Color.White,
    secondary = DeepPurple,
    onSecondary = Color.White,
    background = Color(0xFF121212), // Fondo oscuro estándar
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E), // Superficies oscuras
    onSurface = Color.White
    /* Otros colores por defecto... */
)



@Composable
fun POrOZCoMusicAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Desactivamos Dynamic color para asegurar que los colores del mock sean consistentes
    dynamicColor: Boolean = false, // !dynamicColor
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme // Usamos el esquema claro ajustado para el mock
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asume que tienes un Typography.kt configurado
        content = content
    )
}