package com.worldline.kmm.ui


enum class MaterialColor(
    val tones: Map<Int, Int> = emptyMap(),
    val accentTones: Map<Int, Int> = emptyMap(),
    val singleColor: Int? = null
) {
    RED(
        tones = mapOf(
            50 to 0xFFEBEE,
            100 to 0xFFCDD2,
            200 to 0xEF9A9A,
            300 to 0xE57373,
            400 to 0xEF5350,
            500 to 0xF44336,
            600 to 0xE53935,
            700 to 0xD32F2F,
            800 to 0xC62828,
            900 to 0xB71C1C
        ),
        accentTones = mapOf(
            100 to 0xFF8A80,
            200 to 0xFF5252,
            400 to 0xFF1744,
            700 to 0xD50000
        )
    ),
    PINK(
        tones = mapOf(
            50 to 0xFCE4EC,
            100 to 0xF8BBD0,
            200 to 0xF48FB1,
            300 to 0xF06292,
            400 to 0xEC407A,
            500 to 0xE91E63,
            600 to 0xD81B60,
            700 to 0xC2185B,
            800 to 0xAD1457,
            900 to 0x880E4F
        ),
        accentTones = mapOf(
            100 to 0xFF80AB,
            200 to 0xFF4081,
            400 to 0xF50057,
            700 to 0xC51162
        )
    ),
    PURPLE(
        tones = mapOf(
            50 to 0xF3E5F5,
            100 to 0xE1BEE7,
            200 to 0xCE93D8,
            300 to 0xBA68C8,
            400 to 0xAB47BC,
            500 to 0x9C27B0,
            600 to 0x8E24AA,
            700 to 0x7B1FA2,
            800 to 0x6A1B9A,
            900 to 0x4A148C
        ),
        accentTones = mapOf(
            100 to 0xEA80FC,
            200 to 0xE040FB,
            400 to 0xD500F9,
            700 to 0xAA00FF
        )
    ),
    DEEP_PURPLE(
        tones = mapOf(
            50 to 0xEDE7F6,
            100 to 0xD1C4E9,
            200 to 0xB39DDB,
            300 to 0x9575CD,
            400 to 0x7E57C2,
            500 to 0x673AB7,
            600 to 0x5E35B1,
            700 to 0x512DA8,
            800 to 0x4527A0,
            900 to 0x311B92
        ),
        accentTones = mapOf(
            100 to 0xB388FF,
            200 to 0x7C4DFF,
            400 to 0x651FFF,
            700 to 0x6200EA
        )
    ),
    INDIGO(
        tones = mapOf(
            50 to 0xE8EAF6,
            100 to 0xC5CAE9,
            200 to 0x9FA8DA,
            300 to 0x7986CB,
            400 to 0x5C6BC0,
            500 to 0x3F51B5,
            600 to 0x3949AB,
            700 to 0x303F9F,
            800 to 0x283593,
            900 to 0x1A237E
        ),
        accentTones = mapOf(
            100 to 0x8C9EFF,
            200 to 0x536DFE,
            400 to 0x3D5AFE,
            700 to 0x304FFE
        )
    ),
    BLUE(
        tones = mapOf(
            50 to 0xE3F2FD,
            100 to 0xBBDEFB,
            200 to 0x90CAF9,
            300 to 0x64B5F6,
            400 to 0x42A5F5,
            500 to 0x2196F3,
            600 to 0x1E88E5,
            700 to 0x1976D2,
            800 to 0x1565C0,
            900 to 0x0D47A1
        ),
        accentTones = mapOf(
            100 to 0x82B1FF,
            200 to 0x448AFF,
            400 to 0x2979FF,
            700 to 0x2962FF
        )
    ),
    LIGHT_BLUE(
        tones = mapOf(
            50 to 0xE1F5FE,
            100 to 0xB3E5FC,
            200 to 0x81D4FA,
            300 to 0x4FC3F7,
            400 to 0x29B6F6,
            500 to 0x03A9F4,
            600 to 0x039BE5,
            700 to 0x0288D1,
            800 to 0x0277BD,
            900 to 0x01579B
        ),
        accentTones = mapOf(
            100 to 0x80D8FF,
            200 to 0x40C4FF,
            400 to 0x00B0FF,
            700 to 0x0091EA
        )
    ),
    CYAN(
        tones = mapOf(
            50 to 0xE0F7FA,
            100 to 0xB2EBF2,
            200 to 0x80DEEA,
            300 to 0x4DD0E1,
            400 to 0x26C6DA,
            500 to 0x00BCD4,
            600 to 0x00ACC1,
            700 to 0x0097A7,
            800 to 0x00838F,
            900 to 0x006064
        ),
        accentTones = mapOf(
            100 to 0x84FFFF,
            200 to 0x18FFFF,
            400 to 0x00E5FF,
            700 to 0x00B8D4
        )
    ),
    TEAL(
        tones = mapOf(
            50 to 0xE0F2F1,
            100 to 0xB2DFDB,
            200 to 0x80CBC4,
            300 to 0x4DB6AC,
            400 to 0x26A69A,
            500 to 0x009688,
            600 to 0x00897B,
            700 to 0x00796B,
            800 to 0x00695C,
            900 to 0x004D40
        ),
        accentTones = mapOf(
            100 to 0xA7FFEB,
            200 to 0x64FFDA,
            400 to 0x1DE9B6,
            700 to 0x00BFA5
        )
    ),
    GREEN(
        tones = mapOf(
            50 to 0xE8F5E9,
            100 to 0xC8E6C9,
            200 to 0xA5D6A7,
            300 to 0x81C784,
            400 to 0x66BB6A,
            500 to 0x4CAF50,
            600 to 0x43A047,
            700 to 0x388E3C,
            800 to 0x2E7D32,
            900 to 0x1B5E20
        ),
        accentTones = mapOf(
            100 to 0xB9F6CA,
            200 to 0x69F0AE,
            400 to 0x00E676,
            700 to 0x00C853
        )
    ),
    LIGHT_GREEN(
        tones = mapOf(
            50 to 0xF1F8E9,
            100 to 0xDCEDC8,
            200 to 0xC5E1A5,
            300 to 0xAED581,
            400 to 0x9CCC65,
            500 to 0x8BC34A,
            600 to 0x7CB342,
            700 to 0x689F38,
            800 to 0x558B2F,
            900 to 0x33691E
        ),
        accentTones = mapOf(
            100 to 0xCCFF90,
            200 to 0xB2FF59,
            400 to 0x76FF03,
            700 to 0x64DD17
        )
    ),
    LIME(
        tones = mapOf(
            50 to 0xF9FBE7,
            100 to 0xF0F4C3,
            200 to 0xE6EE9C,
            300 to 0xDCE775,
            400 to 0xD4E157,
            500 to 0xCDDC39,
            600 to 0xC0CA33,
            700 to 0xAFB42B,
            800 to 0x9E9D24,
            900 to 0x827717
        ),
        accentTones = mapOf(
            100 to 0xF4FF81,
            200 to 0xEEFF41,
            400 to 0xC6FF00,
            700 to 0xAEEA00
        )
    ),
    YELLOW(
        tones = mapOf(
            50 to 0xFFFDE7,
            100 to 0xFFF9C4,
            200 to 0xFFF59D,
            300 to 0xFFF176,
            400 to 0xFFEE58,
            500 to 0xFFEB3B,
            600 to 0xFDD835,
            700 to 0xFBC02D,
            800 to 0xF9A825,
            900 to 0xF57F17
        ),
        accentTones = mapOf(
            100 to 0xFFFF8D,
            200 to 0xFFFF00,
            400 to 0xFFEA00,
            700 to 0xFFD600
        )
    ),
    AMBER(
        tones = mapOf(
            50 to 0xFFF8E1,
            100 to 0xFFECB3,
            200 to 0xFFE082,
            300 to 0xFFD54F,
            400 to 0xFFCA28,
            500 to 0xFFC107,
            600 to 0xFFB300,
            700 to 0xFFA000,
            800 to 0xFF8F00,
            900 to 0xFF6F00
        ),
        accentTones = mapOf(
            100 to 0xFFE57F,
            200 to 0xFFD740,
            400 to 0xFFC400,
            700 to 0xFFAB00
        )
    ),
    ORANGE(
        tones = mapOf(
            50 to 0xFFF3E0,
            100 to 0xFFE0B2,
            200 to 0xFFCC80,
            300 to 0xFFB74D,
            400 to 0xFFA726,
            500 to 0xFF9800,
            600 to 0xFB8C00,
            700 to 0xF57C00,
            800 to 0xEF6C00,
            900 to 0xE65100
        ),
        accentTones = mapOf(
            100 to 0xFFD180,
            200 to 0xFFAB40,
            400 to 0xFF9100,
            700 to 0xFF6D00
        )
    ),
    DEEP_ORANGE(
        tones = mapOf(
            50 to 0xFBE9E7,
            100 to 0xFFCCBC,
            200 to 0xFFAB91,
            300 to 0xFF8A65,
            400 to 0xFF7043,
            500 to 0xFF5722,
            600 to 0xF4511E,
            700 to 0xE64A19,
            800 to 0xD84315,
            900 to 0xBF360C
        ),
        accentTones = mapOf(
            100 to 0xFF9E80,
            200 to 0xFF6E40,
            400 to 0xFF3D00,
            700 to 0xDD2C0
        )
    ),
    BROWN(
        tones = mapOf(
            50 to 0xEFEBE9,
            100 to 0xD7CCC8,
            200 to 0xBCAAA4,
            300 to 0xA1887F,
            400 to 0x8D6E63,
            500 to 0x795548,
            600 to 0x6D4C41,
            700 to 0x5D4037,
            800 to 0x4E342E,
            900 to 0x3E2723
        )
    ),
    GREY(
        tones = mapOf(
            50 to 0xFAFAFA,
            100 to 0xF5F5F5,
            200 to 0xEEEEEE,
            300 to 0xE0E0E0,
            400 to 0xBDBDBD,
            500 to 0x9E9E9E,
            600 to 0x757575,
            700 to 0x616161,
            800 to 0x424242,
            900 to 0x212121
        )
    ),
    BLUE_GREY(
        tones = mapOf(
            50 to 0xECEFF1,
            100 to 0xCFD8DC,
            200 to 0xB0BEC5,
            300 to 0x90A4AE,
            400 to 0x78909C,
            500 to 0x607D8B,
            600 to 0x546E7A,
            700 to 0x455A64,
            800 to 0x37474F,
            900 to 0x263238
        )
    ),
    BLACK(singleColor = COLOR_BLACK),
    WHITE(singleColor = COLOR_WHITE);

    val accent = object {
        operator fun get(tone: Int): Int =
            singleColor ?: getAccent(tone) ?: getTone(tone) ?: COLOR_BLACK

        operator fun invoke(): Int = singleColor ?: getAccent(400) ?: getTone(500) ?: COLOR_BLACK
    }

    operator fun get(tone: Int): Int = singleColor ?: getTone(tone) ?: COLOR_BLACK
    operator fun invoke(): Int = singleColor ?: getTone(500) ?: COLOR_BLACK

    fun getTone(tone: Int): Int? =
        if (tones.isEmpty()) null
        else tones[tone] ?: interpolate(tones, tone)

    fun getAccent(tone: Int): Int? =
        if (accentTones.isEmpty()) null
        else accentTones[tone] ?: interpolate(accentTones, tone)

    fun interpolate(tones: Map<Int, Int>, tone: Int): Int {
        val lowTone = tones.keys.filter { tone >= it }.maxOrNull() ?: 0
        val highTone = tones.keys.filter { tone <= it }.minOrNull() ?: 1000
        val lowColor = tones[lowTone] ?: COLOR_WHITE
        val highColor = tones[highTone] ?: COLOR_BLACK
        return colorInt(
            interpolateChannel(lowTone, highTone, tone, lowColor.red(), highColor.red()),
            interpolateChannel(lowTone, highTone, tone, lowColor.green(), highColor.green()),
            interpolateChannel(lowTone, highTone, tone, lowColor.blue(), highColor.blue())
        )
    }

    fun withAlpha(tone: Int, alpha: Int): Long {
        val colorTone =
            tones[tone]?.toLong() ?: throw IllegalArgumentException("Tone $tone not fount")
        return colorTone + when (alpha) {
            100 -> 0xFF000000
            95 -> 0xF2000000
            90 -> 0xE6000000
            85 -> 0xD9000000
            80 -> 0xCC000000
            75 -> 0xBF000000
            70 -> 0xB3000000
            65 -> 0xA6000000
            60 -> 0x99000000
            55 -> 0x8C000000
            50 -> 0x80000000
            45 -> 0x73000000
            40 -> 0x66000000
            35 -> 0x59000000
            30 -> 0x4D000000
            25 -> 0x40000000
            20 -> 0x33000000
            15 -> 0x26000000
            10 -> 0x1A000000
            5 -> 0x0D000000
            0 -> 0x00000000
            else -> throw IllegalArgumentException("Alpha $alpha is not multiple of 5")
        }


    }

    fun interpolateChannel(
        lowTone: Int,
        highTone: Int,
        tone: Int,
        lowValue: Int,
        highValue: Int
    ): Int =
        (lowValue * (highTone - tone) + highValue * (tone - lowTone)) / (highTone - lowTone)
}

private const val COLOR_BLACK = 0x000000
private const val COLOR_WHITE = 0xFFFFFF

fun Int.red() = this and 0xFF0000 ushr 16
fun Int.green() = this and 0x00FF00 ushr 8
fun Int.blue() = this and 0x0000FF

private fun colorInt(red: Int, green: Int, blue: Int) = (red shl 16) + (green shl 8) + blue