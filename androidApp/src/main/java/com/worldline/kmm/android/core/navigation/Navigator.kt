package com.worldline.kmm.android.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.worldline.kmm.android.exhaustive
import com.worldline.kmm.android.ui.home.poidetail.PoiDetailRoute
import com.worldline.kmm.android.ui.home.poilist.PoiListRoute
import com.worldline.kmm.android.ui.home.poilist.stateWithLifecycle
import com.worldline.kmm.viewmodel.NavigationEvent.Detail
import com.worldline.kmm.viewmodel.NavigationEvent.Home
import com.worldline.kmm.viewmodel.NavigationState
import com.worldline.kmm.viewmodel.NavigationViewModel

@Composable
fun PoiApp(navController: NavHostController = rememberNavController()) {

    val navigationViewModel = remember { NavigationViewModel() }

    NavHost(
        navController = navController,
        startDestination = Home.List.route
    ) {
        composable(route = Home.List.route) {
            PoiListRoute(onNavigationEvent = navigationViewModel::onEvent)
        }

        composable(route = Detail().route) {
            PoiDetailRoute(poiId = it.arguments!!.getString("poiId")!!.toLong())
        }
    }

    val state = navigationViewModel.stateWithLifecycle().value

    Log.i("Composable app changed", "PoiApp: $state")

    when (state) {
        is NavigationState.Detail -> navController.navigate(Detail(state.id).createRoute())
        NavigationState.Home.List -> navController.navigate(Home.List.route)
        NavigationState.Home.Map -> TODO()
    }.exhaustive
}