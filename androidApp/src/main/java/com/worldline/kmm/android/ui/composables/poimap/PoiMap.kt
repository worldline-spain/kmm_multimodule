package com.worldline.kmm.android.ui.composables.poimap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.worldline.kmm.android.ui.composables.poilist.stateWithLifecycle
import com.worldline.kmm.ui.logic.poilistvm.PoiListEvent
import com.worldline.kmm.ui.logic.poilistvm.PoiListState
import com.worldline.kmm.ui.logic.poilistvm.PoiListViewModel

@Composable
fun PoiMapRoute() {
    val viewModel = remember { PoiListViewModel() }

    PoiMapContent(viewModel.stateWithLifecycle().value, onEvent = viewModel::onEvent)
}

@Composable
fun PoiMapContent(state: PoiListState, onEvent: (PoiListEvent) -> Unit) {

    AndroidView(
        factory = { context ->
            MapView(context).apply {
                val map = this.getMapboxMap()
                map.loadStyleUri(Style.MAPBOX_STREETS)
            }
        }
    )

    LaunchedEffect(Unit) {
        onEvent(PoiListEvent.Attach)
    }
}

@Preview
@Composable
fun PoiMapPreview() {

}
