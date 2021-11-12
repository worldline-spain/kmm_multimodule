package com.worldline.kmm.android.core.navigation

sealed class HomeRoutes(val routeName: String) {
    object PoiList : HomeRoutes("poiList")

    companion object {
        const val ARG_POI_ID = "poiId"
    }
}