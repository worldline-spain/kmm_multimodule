package com.worldline.kmm.android.ui.home.poidetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.worldline.kmm.ui.logic.poilistvm.PoiDetailEvent
import com.worldline.kmm.ui.logic.poilistvm.PoiDetailViewModel

@Composable
fun PoiDetailRoute(poiId: Long) {
    val viewModel = remember { PoiDetailViewModel(poiId) }
    Text(text = "Hello $poiId")

    LaunchedEffect(Unit) {
        viewModel.onEvent(PoiDetailEvent.Attach)
    }
}