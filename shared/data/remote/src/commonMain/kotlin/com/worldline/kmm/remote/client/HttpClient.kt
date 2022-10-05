package com.worldline.kmm.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

val json: Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
    encodeDefaults = true
}


internal fun <T> URLBuilder.params(vararg params: Map.Entry<String, T>) {
    parameters.apply {
        params.forEach {
            append(it.key, it.value.toString())
        }
    }
}

internal fun URLBuilder.withPath(path: String, block: URLBuilder.(URLBuilder) -> Unit = {}) {
    encodedPath = path
    block(this)
}

internal fun buildClient(
    endpoint: String,
    isDebug: Boolean,
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient =
    HttpClient {
        defaultRequest {
            val endpointUrlBuilder = URLBuilder(endpoint)
            url {
                protocol = endpointUrlBuilder.protocol
                host = endpointUrlBuilder.host
            }
        }
        if (isDebug) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
        install(ContentNegotiation) {
            TODO()
        }
        block(this)
    }
