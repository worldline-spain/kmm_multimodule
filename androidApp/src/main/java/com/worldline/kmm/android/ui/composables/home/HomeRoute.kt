package com.worldline.kmm.android.ui.composables.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.worldline.kmm.android.exhaustive
import com.worldline.kmm.android.ui.composables.poilist.PoiListRoute
import com.worldline.kmm.android.ui.composables.poilist.stateWithLifecycle
import com.worldline.kmm.home.logic.HomeEvent
import com.worldline.kmm.home.logic.HomeState
import com.worldline.kmm.home.logic.HomeViewModel
import com.worldline.kmm.viewmodel.NavigationEvent

@Composable
fun HomeRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { HomeViewModel() }

    HomeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {

    Scaffold(
        bottomBar = { HomeBottomBar(onEvent) },
    ) {
        when (state) {
            HomeState.List -> PoiListRoute(onNavigationEvent = onNavigationEvent)
            HomeState.Map -> Text(text = "Map")
        }.exhaustive
    }

    LaunchedEffect(Unit) {
        onEvent(HomeEvent.Attach)
    }
}

@Composable
fun HomeBottomBar(onEvent: (HomeEvent) -> Unit) {
    BottomNavigation {
        BottomNavigationItem(
            selected = false,
            onClick = { onEvent(HomeEvent.List) },
            icon = { Icon(Icons.Filled.List, contentDescription = "Test") },
            label = { Text(text = "List") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { onEvent(HomeEvent.Map) },
            icon = { Icon(Icons.Filled.List, contentDescription = "Test") },
            label = { Text(text = "Map") }
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeContent(state = HomeState.List, onEvent = {}, onNavigationEvent = {})
}