package com.worldline.kmm.android.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

/** Colors **/
val colorPrimary = Color.White
val colorPrimaryDark = Color.Black
val colorAccent = Color.Black

val LightColors = lightColors(
    primary = colorPrimary,
    primaryVariant = colorAccent,
    secondary = Color.Black
)

val DarkColors = darkColors(
    primary = colorPrimaryDark,
    primaryVariant = Color.Gray,
    secondary = Color.White
)

