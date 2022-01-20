package com.worldline.kmm.android.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.worldline.kmm.android.exhaustive
import com.worldline.kmm.android.ui.composables.home.HomeRoute
import com.worldline.kmm.android.ui.composables.poidetail.PoiDetailRoute
import com.worldline.kmm.android.ui.composables.poilist.stateWithLifecycle
import com.worldline.kmm.viewmodel.NavigationEvent.Detail
import com.worldline.kmm.viewmodel.NavigationEvent.Home
import com.worldline.kmm.viewmodel.NavigationState
import com.worldline.kmm.viewmodel.NavigationViewModel

@Composable
fun PoiApp(navController: NavHostController = rememberNavController()) {

    val navigationViewModel = remember { NavigationViewModel() }

    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        composable(route = Home.route) {
            HomeRoute(onNavigationEvent = navigationViewModel::onEvent)
        }

        composable(route = Detail().route) {
            PoiDetailRoute(poiId = it.arguments!!.getString("poiId")!!.toLong())
        }
    }

    val state = navigationViewModel.stateWithLifecycle().value

    Log.i("Composable app changed", "PoiApp: $state")

    when (state) {
        is NavigationState.Detail -> navController.navigate(Detail(state.id).createRoute())
        NavigationState.Home -> navController.navigate(Home.route)
    }.exhaustive
}