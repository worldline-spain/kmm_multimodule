package com.worldline.kmm.local

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.core.Success
import com.worldline.kmm.local.extension.execute

class SharedMemoryPoiLocal : PoiLocal {

    private val pois = mutableListOf<Poi>()

    override suspend fun getAllPois(): Either<Error, List<Poi>> = execute {
        if (pois.isEmpty()) {
            throw Error.Poi.EmptyList
        }
        pois
    }

    override suspend fun getPoiById(id: Long): Either<Error, Poi> = execute {
        pois.first { poi -> poi.id == id }
    }

    override suspend fun savePois(list: List<Poi>): Either<Error, Success> = execute {
        pois.clear()
        pois.addAll(list)
        Success
    }
}