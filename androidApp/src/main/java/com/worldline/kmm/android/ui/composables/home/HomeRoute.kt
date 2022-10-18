package com.worldline.kmm.android.ui.composables.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.worldline.kmm.android.exhaustive
import com.worldline.kmm.android.ui.composables.poilist.PoiListRoute
import com.worldline.kmm.android.ui.composables.poilist.stateWithLifecycle
import com.worldline.kmm.android.ui.composables.poimap.PoiMapRoute
import com.worldline.kmm.feature.viewModels.logic.HomeEvent
import com.worldline.kmm.feature.viewModels.logic.HomeState
import com.worldline.kmm.feature.viewModels.logic.HomeViewModel
import com.worldline.kmm.ui.MaterialColor
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
        backgroundColor = Color(MaterialColor.AMBER.tone(600, 100))
    ) {
        BottomNavigationItem(
            selected = false,
            onClick = { onEvent(HomeEvent.List) },
            icon = { Icon(Icons.Filled.List, contentDescription = "Test") },
            label = { Text(text = "List") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { onEvent(HomeEvent.Map) },
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Test") },
            label = { Text(text = "Map") }
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeContent(state = HomeState.List, onEvent = {}, onNavigationEvent = {})
}