package com.worldline.kmm.android.ui.composables.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.worldline.kmm.android.exhaustive
import com.worldline.kmm.android.ui.composables.poilist.PoiListRoute
import com.worldline.kmm.android.ui.composables.poilist.stateWithLifecycle
import com.worldline.kmm.android.ui.composables.poimap.PoiMapRoute
import com.worldline.kmm.feature.viewModels.logic.HomeEvent
import com.worldline.kmm.feature.viewModels.logic.HomeState
import com.worldline.kmm.feature.viewModels.logic.HomeViewModel
import com.worldline.kmm.ui.MaterialColor

@Composable
fun HomeRoute(navController: NavHostController, viewModel: HomeViewModel) {
    HomeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        navController = navController
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    navController: NavHostController
) {

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "KMM Android Jetpack Compose") }) },
        bottomBar = { HomeBottomBar(onEvent) },
    ) {
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                HomeState.List -> PoiListRoute(onNavigationEvent = onNavigationEvent)
                HomeState.Map -> PoiMapRoute(onNavigationEvent = onNavigationEvent)
            }.exhaustive
        }
    }

    LaunchedEffect(Unit) {
        onEvent(HomeEvent.Attach)
    }
}

@Composable
fun HomeBottomBar(onEvent: (HomeEvent) -> Unit) {
    BottomNavigation(
        backgroundColor = Color(MaterialColor.TEAL.tone(600, 100))
    ) {
        BottomNavigationItem(
            selected = false,
            onClick = { onEvent(HomeEvent.List) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "Test",
                    tint = Color.White
                )
            },
            label = { Text(text = "List") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { onEvent(HomeEvent.Map) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Test",
                    tint = Color.White
                )
            },
            label = { Text(text = "Map") }
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeContent(state = HomeState.List, onEvent = {}, onNavigationEvent = {})
}