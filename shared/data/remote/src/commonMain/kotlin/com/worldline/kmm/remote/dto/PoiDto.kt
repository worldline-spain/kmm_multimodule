package com.worldline.kmm.remote.dto

import kotlinx.serialization.Serializable

@Serializable
class PoiDto(
    val id: String,
    val title: String,
    val geocoordinates: String,
    val image: String
)