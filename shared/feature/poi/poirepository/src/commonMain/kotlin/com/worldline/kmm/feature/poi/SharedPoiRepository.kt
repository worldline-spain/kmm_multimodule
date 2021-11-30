package com.worldline.kmm.feature.poi

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.local.PoiLocal
import com.worldline.kmm.remote.PoiRemote

class SharedPoiRepository(
    private val local: PoiLocal,
    private val remote: PoiRemote
) : PoiRepository {

    override suspend fun getAll(force: Boolean): Either<Error, List<Poi>> {
        return if (force) {
            remote.getAllPois().ifRight {
                local.savePois(it.success)
                it
            }
        } else {
            when (val dbResult = local.getAllPois()) {
                is Either.Left -> if (dbResult.error is Error.Poi.EmptyList) {
                    remote.getAllPois().ifRight {
                        local.savePois(it.success)
                        it
                    }
                } else {
                    dbResult
                }
                is Either.Right -> dbResult
            }
        }
    }

    override suspend fun getById(id: Long): Either<Error, Poi> = local.getPoiById(id)
}