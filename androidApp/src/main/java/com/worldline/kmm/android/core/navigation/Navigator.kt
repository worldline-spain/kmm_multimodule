package com.worldline.kmm.android.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.worldline.kmm.android.exhaustive
import com.worldline.kmm.android.ui.home.poilist.PoiListRoute
import com.worldline.kmm.android.ui.home.poilist.stateWithLifecycle
import com.worldline.kmm.ui.logic.poilistvm.PoiListViewModel
import com.worldline.kmm.viewmodel.NavigationState
import com.worldline.kmm.viewmodel.NavigationViewModel


private val navigationViewModel by lazy { NavigationViewModel() }
private val poiListViewModel by lazy { PoiListViewModel() }

@Composable
fun PoiApp(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = HomeRoutes.PoiList.routeName) {
        composable(route = HomeRoutes.PoiList.routeName) {
            PoiListRoute(poiListViewModel)
        }
    }

    val state = navigationViewModel.stateWithLifecycle().value

    when (state) {
        is NavigationState.Detail -> TODO()
        NavigationState.Home.List -> navController.navigate(HomeRoutes.PoiList.routeName)
        NavigationState.Home.Map -> TODO()
    }.exhaustive
}