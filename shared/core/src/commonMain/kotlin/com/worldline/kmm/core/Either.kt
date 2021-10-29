package com.worldline.kmm.core

sealed class Either<L, R> {
    class Left<L, R>(val error: L) : Either<L, R>() {
        override fun toString(): String = "Left $error"
    }

    class Right<L, R>(val success: R) : Either<L, R>() {
        override fun toString(): String = "Right $success"
    }

    infix fun <Rp> map(f: (R) -> (Either<L, Rp>)): Either<L, Rp> {
        return when (this) {
            is Left<L, R> -> Left(this.error)
            is Right<L, R> -> f(this.success)
        }
    }

    suspend infix fun <Rp> ifRight(f: suspend (Right<L, R>) -> Either<L, Rp>): Either<L, Rp> {
        return when (this) {
            is Left -> Left(error)
            is Right -> f(this)
        }
    }

    suspend infix fun <Lp> ifLeft(f: suspend (Left<L, R>) -> Either<Lp, R>): Either<Lp, R> {
        return when (this) {
            is Left -> f(this)
            is Right -> Right(success)
        }
    }

    infix fun <Rp> seq(e: Either<L, Rp>): Either<L, Rp> = e

    fun fold(error: (L) -> Unit, success: (R) -> Unit) {
        when (this) {
            is Left -> error(this.error)
            is Right -> success(this.success)
        }
    }

    suspend fun foldSuspend(error: (L) -> Unit, success: suspend (R) -> Unit) {
        when (this) {
            is Left -> error(this.error)
            is Right -> success(this.success)
        }
    }
}
