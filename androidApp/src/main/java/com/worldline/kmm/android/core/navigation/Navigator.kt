package com.worldline.kmm.android.core.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.worldline.kmm.android.ui.home.poilist.PoiListScreen
import com.worldline.kmm.ui.logic.poilistvm.PoiListViewModel

class Navigator {

    @ExperimentalComposeUiApi
    @Composable
    fun HomeNavigation(activity: ComponentActivity) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeRoutes.PoiList.routeName) {
            composable(route = HomeRoutes.PoiList.routeName) {
                val poiListViewModel = ViewModelProvider(activity)[PoiListViewModel::class.java]
                PoiListScreen(
                    state = poiListViewModel.state.collectAsState().value,
                    onTriggerEvent = poiListViewModel::onTriggerEvent,
                )
            }
        }
    }
}