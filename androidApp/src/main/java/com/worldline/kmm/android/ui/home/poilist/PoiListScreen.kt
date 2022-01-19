package com.worldline.kmm.android.ui.home.poilist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import com.worldline.kmm.android.R
import com.worldline.kmm.android.theme.AppTheme
import com.worldline.kmm.core.Poi
import com.worldline.kmm.ui.logic.poilistvm.PoiListEvent
import com.worldline.kmm.ui.logic.poilistvm.PoiListState
import com.worldline.kmm.ui.logic.poilistvm.PoiListViewModel
import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState

@Composable
fun <V : ViewState> RootViewModel<V>.stateWithLifecycle(): State<V> {
    val lifecycleOwner = LocalLifecycleOwner.current

    val flow = remember(state, lifecycleOwner) {
        state.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    return flow.collectAsState(state.value)
}

@Composable
fun PoiListRoute(viewModel: PoiListViewModel) {

    PoiListContent(state = viewModel.stateWithLifecycle().value) {
        viewModel.onEvent(it)
    }
}

@Composable
fun PoiListContent(state: PoiListState, onEvent: (PoiListEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.poi_list_title),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.secondary
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        },
        content = {
            when (state) {
                is PoiListState.InProgress -> LoadingView()
                is PoiListState.Success -> PoiListSuccessView(state)
                is PoiListState.Error -> EmptyView()
            }
        })

    LaunchedEffect(Unit) {
        onEvent(PoiListEvent.Attach)
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center),
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
private fun EmptyView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.poi_list_empty),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
private fun PoiListSuccessView(
    poisResponse: PoiListState.Success,
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(
                all = 16.dp
            )
    ) {
        items(poisResponse.pois) { poi -> PoiCard(poi = poi) }
    }
}

@Composable
fun PoiCard(poi: Poi) {
    Row {
        Text(
            text = poi.title,
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.secondary),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(MaterialTheme.colors.primary)
                .padding(vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun EmptyViewPreview() {
    AppTheme {
        EmptyView()
    }
}

@Preview
@Composable
fun PoiCardPreview() {
    val poi = Poi(
        id = 1,
        title = "234",
        latitude = 41.403706,
        longitude = 2.173504,
    )
    AppTheme {
        PoiCard(poi = poi)
    }
}

@Preview
@Composable
fun PoiCardListPreview() {
    val poi1 = Poi(
        id = 1,
        title = "Sagrada Família",
        latitude = 41.403706,
        longitude = 2.173504,
    )
    val poi2 = Poi(
        id = 2,
        title = "Casa Batlló",
        latitude = 41.391902536329894,
        longitude = 2.1649997898845004,
    )
    AppTheme {
        PoiListContent(state = PoiListState.Success(listOf(poi1, poi2))) {}
    }
}
