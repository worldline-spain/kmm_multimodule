package com.worldline.kmm.feature.repository.logic

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi

interface Repository {
    suspend fun getAllPOIs(force: Boolean): Either<Error, List<Poi>>
    suspend fun getPOIById(id: Long): Either<Error, Poi>
}