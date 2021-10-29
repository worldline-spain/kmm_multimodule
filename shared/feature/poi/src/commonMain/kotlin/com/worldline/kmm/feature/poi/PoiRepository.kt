package com.worldline.kmm.feature.poi

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi

interface PoiRepository {
    suspend fun getAll(force: Boolean): Either<Error, List<Poi>>
    suspend fun getById(id: Long): Either<Error, Poi>
}