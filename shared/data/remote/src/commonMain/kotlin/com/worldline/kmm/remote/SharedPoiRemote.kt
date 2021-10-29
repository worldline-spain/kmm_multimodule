package com.worldline.kmm.remote

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.remote.client.buildClient
import com.worldline.kmm.remote.client.withPath
import com.worldline.kmm.remote.dto.PoiResponseDto
import com.worldline.kmm.remote.extension.execute
import com.worldline.kmm.remote.mapper.toModel
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class SharedPoiRemote : PoiRemote {

    companion object {
        private const val ENDPOINT = "https://t21services.herokuapp.com"
    }

    private val http
        get() = buildClient(ENDPOINT, isDebug = false)

    override suspend fun getAllPois(): Either<Error, List<Poi>> = execute {
        http.use {
            it.get<PoiResponseDto> {
                url.withPath("/points")
            }.list.map { it.toModel() }
        }
    }
}