package com.boycoder.todo.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/**
 * @Author: zhutao
 * @desc:
 */

private val DarkColorPalette = darkColors(
    primary = Lavender4,
    primaryVariant = Lavender5,
    secondary = Ocean6
)

private val LightColorPalette = lightColors(
    primary = Lavender4,
    primaryVariant = Lavender5,
    secondary = Ocean6
)

@Composable
fun ToDoComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}