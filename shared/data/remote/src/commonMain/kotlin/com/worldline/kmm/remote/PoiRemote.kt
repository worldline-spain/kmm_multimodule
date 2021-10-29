package com.worldline.kmm.remote

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi


interface PoiRemote {
    suspend fun getAllPois(): Either<Error, List<Poi>>
}