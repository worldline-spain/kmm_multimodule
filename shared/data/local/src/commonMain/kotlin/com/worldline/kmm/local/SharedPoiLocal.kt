package com.worldline.kmm.local

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.core.Success
import com.worldline.kmm.db.kmm
import com.worldline.kmm.local.driver.DbDriverFactory
import com.worldline.kmm.local.extension.execute
import toModel
import toVo

class SharedPoiLocal(driver: DbDriverFactory) : PoiLocal {

    private val db by lazy { kmm(driver.createDriver()).poiVoQueries }

    override suspend fun getAllPois(): Either<Error, List<Poi>> = execute {
        val list = db.getAll().executeAsList().map { it.toModel() }
        if (list.isEmpty()) {
            throw Error.Poi.EmptyList
        }
        list
    }

    override suspend fun getPoiById(id: Long): Either<Error, Poi> = execute {
        db.getById(id).executeAsOne().toModel()
    }

    override suspend fun savePois(list: List<Poi>): Either<Error, Success> = execute {
        db.transaction {
            list.forEach {
                db.insert(it.toVo())
            }
        }
        Success
    }
}