package com.worldline.kmm.android.ui.composables.poimap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.worldline.kmm.android.R
import com.worldline.kmm.android.exhaustive
import com.worldline.kmm.android.ui.composables.poilist.EmptyView
import com.worldline.kmm.android.ui.composables.poilist.LoadingView
import com.worldline.kmm.android.ui.composables.poilist.stateWithLifecycle
import com.worldline.kmm.core.Poi
import com.worldline.kmm.ui.logic.poilistvm.PoiListEvent
import com.worldline.kmm.ui.logic.poilistvm.PoiListState
import com.worldline.kmm.ui.logic.poilistvm.PoiListViewModel
import com.worldline.kmm.viewmodel.NavigationEvent

@Composable
fun PoiMapRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { PoiListViewModel(onNavigationEvent = onNavigationEvent) }

    PoiMapContent(viewModel.stateWithLifecycle().value, onEvent = viewModel::onEvent)
}

@Composable
fun PoiMapContent(state: PoiListState, onEvent: (PoiListEvent) -> Unit) {

    when (state) {
        is PoiListState.Error -> EmptyView()
        PoiListState.InProgress -> LoadingView()
        is PoiListState.Success -> MapSuccessView(state.pois)
    }.exhaustive

    LaunchedEffect(Unit) {
        onEvent(PoiListEvent.Attach)
    }
}

@Composable
fun MapSuccessView(pois: List<Poi>) {
    AndroidView(
        factory = { context ->
            MapView(context).apply {
                val map = this.getMapboxMap()
                map.loadStyleUri(Style.MAPBOX_STREETS)
                map.addOnStyleLoadedListener {
                    val api = annotations
                    val manager = api.createPointAnnotationManager(this)
                    pois.forEach {
                        val options = PointAnnotationOptions()
                            .withPoint(Point.fromLngLat(it.longitude, it.latitude))
                            .withIconImage(bitmapFromDrawableRes(context, R.drawable.red_marker)!!)
                        manager.create(options)
                    }

                    val coordinates = listOf(
                        pois.map { Point.fromLngLat(it.longitude, it.latitude) }
                    )
                    val polygon = Polygon.fromLngLats(coordinates)
                    val position =
                        map.cameraForGeometry(polygon, EdgeInsets(100.0, 100.0, 100.0, 100.0))
                    map.flyTo(position)
                }

            }
        }
    )
}

private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
    convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
    if (sourceDrawable == null) {
        return null
    }
    return if (sourceDrawable is BitmapDrawable) {
        sourceDrawable.bitmap
    } else {
// copying drawable object to not manipulate on the same reference
        val constantState = sourceDrawable.constantState ?: return null
        val drawable = constantState.newDrawable().mutate()
        val bitmap: Bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth, drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmap
    }
}

@Preview
@Composable
fun PoiMapPreview() {

}
