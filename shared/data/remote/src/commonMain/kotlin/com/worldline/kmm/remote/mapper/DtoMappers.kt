package com.worldline.kmm.remote.mapper

import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.remote.dto.PoiDto

internal fun PoiDto.toModel() = Poi(
    id = id.toLongOrNull() ?: throw Error.Poi.InvalidId,
    title = title,
    latitude = geocoordinates.split(",")[0].toDoubleOrNull() ?: throw Error.Poi.InvalidCoordinates,
    longitude = geocoordinates.split(",")[1].toDoubleOrNull() ?: throw Error.Poi.InvalidCoordinates
)