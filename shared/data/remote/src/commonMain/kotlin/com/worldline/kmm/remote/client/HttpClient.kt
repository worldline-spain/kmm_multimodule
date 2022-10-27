package com.worldline.kmm.remote.client

import com.worldline.kmm.remote.client.json
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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
            json(json)
        }
        block(this)
    }
