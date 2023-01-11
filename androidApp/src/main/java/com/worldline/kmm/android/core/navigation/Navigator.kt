package com.worldline.kmm.android.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.worldline.kmm.android.ui.composables.home.HomeRoute
import org.koin.androidx.compose.getViewModel
import com.worldline.kmm.views.logic.HomeViewModel

@Composable
fun PoiApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home.route
    ) {
        composable(route = NavigationRoute.Home.route) {
            HomeRoute(navController = navController, viewModel = getViewModel())
        }

        /*composable(route = Detail().route) {
            PoiDetailRoute(
                poiId = it.arguments!!.getString("poiId")!!.toLong(),
                navController = navController
            )
        }*/
    }

    /*val state = navigationViewModel.stateWithLifecycle().value

    Log.i("Composable app changed", "PoiApp: $state")

    when (state) {
        is NavigationState.Detail -> navController.navigate(Detail(state.id).createRoute())
        NavigationState.Home -> navController.navigate(NavigationRoute.Home.route)
    }.exhaustive*/
}