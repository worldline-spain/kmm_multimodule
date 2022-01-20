package com.worldline.kmm.android.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.worldline.kmm.ui.MaterialColor

/** Colors **/
val colorPrimary = Color(MaterialColor.INDIGO.tone(500))
val colorPrimaryDark = Color(MaterialColor.INDIGO.tone(800))
val colorAccent = Color(MaterialColor.AMBER.tone(500))

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

