package com.worldline.kmm.remote.client

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

internal val json: Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
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
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        block(this)
    }
