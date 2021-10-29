package com.worldline.kmm.core

sealed class Error : Throwable() {
    object Unknown : Error()
    object NotFound : Error()

    sealed class Poi : Error() {
        object InvalidId : Poi()
        object InvalidCoordinates : Poi()
        object EmptyList : Poi()
    }
}