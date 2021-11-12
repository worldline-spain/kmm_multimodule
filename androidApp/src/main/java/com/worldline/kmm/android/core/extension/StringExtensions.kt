package com.worldline.wlghostcard.core.extension

fun String.isDigitsOnly() = all(Char::isDigit) && isNotEmpty()