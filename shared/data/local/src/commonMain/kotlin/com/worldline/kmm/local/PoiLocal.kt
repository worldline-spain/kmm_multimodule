package com.worldline.kmm.local

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.core.Success

interface PoiLocal {
    suspend fun getAllPois(): Either<Error, List<Poi>>
    suspend fun getPoiById(id: Long): Either<Error, Poi>
    suspend fun savePois(list: List<Poi>): Either<Error, Success>
}