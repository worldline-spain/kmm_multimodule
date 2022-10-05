package com.worldline.kmm.remote

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.remote.client.buildClient
import com.worldline.kmm.remote.client.withPath
import com.worldline.kmm.remote.dto.PoiResponseDto
import com.worldline.kmm.remote.extension.execute
import com.worldline.kmm.remote.mapper.toModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

class SharedPoiRemote : PoiRemote {

    companion object {
        private const val ENDPOINT = "https://sergiocasero.es"
    }

    private val http
        get() = buildClient(ENDPOINT, isDebug = false)

    override suspend fun getAllPois(): Either<Error, List<Poi>> = execute {
        http.use {
            it.get {
                url.withPath("/pois.json")
            }.body<PoiResponseDto>().list.map { it.toModel() }
        }
    }
}