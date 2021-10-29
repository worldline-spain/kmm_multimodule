package com.worldline.kmm.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PoiResponseDto(val list: List<PoiDto>)