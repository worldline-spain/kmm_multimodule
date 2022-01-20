package com.worldline.kmm.android.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.worldline.kmm.ui.MaterialColor

/** Colors **/
val colorPrimary = Color(MaterialColor.INDIGO.withAlpha(500, 100))
val colorPrimaryDark = Color(MaterialColor.INDIGO.withAlpha(800, 100))
val colorAccent = Color(MaterialColor.AMBER.withAlpha(500, 100))

val LightColors = lightColors(
    primary = colorPrimary,
    primaryVariant = colorAccent,
    secondary = Color.Black,
)

val DarkColors = darkColors(
    primary = colorPrimaryDark,
    primaryVariant = Color.Gray,
    secondary = Color.White
)

