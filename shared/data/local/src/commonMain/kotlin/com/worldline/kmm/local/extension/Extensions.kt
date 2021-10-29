package com.worldline.kmm.local.extension

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.local.PoiLocal

internal suspend fun <R> PoiLocal.execute(block: suspend () -> R): Either<Error, R> = try {
    Either.Right(block())
} catch (t: Throwable) {
    t.printStackTrace()
    Either.Left(
        when (t) {
            is Error -> t
            else -> Error.Unknown
        }
    )
}