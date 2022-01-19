package com.worldline.kmm.android.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.worldline.kmm.android.R

val Roboto = FontFamily(
    Font(R.font.roboto),
    Font(R.font.roboto_medium, FontWeight.W500),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val AppTypography = Typography(
    h1 = TextStyle(
        fontFamily = Roboto,
        fontSize = 96.sp
    ),
    h3 = TextStyle(
        fontFamily = Roboto,
        fontSize = 24.sp
    ),
    h5 = TextStyle(
        fontFamily = Roboto,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp
    )
)