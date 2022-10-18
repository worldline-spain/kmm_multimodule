package com.worldline.kmm.android.ui.composables.poidetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.worldline.kmm.android.theme.AppTheme
import com.worldline.kmm.android.ui.composables.poilist.EmptyView
import com.worldline.kmm.android.ui.composables.poilist.LoadingView
import com.worldline.kmm.android.ui.composables.poilist.stateWithLifecycle
import com.worldline.kmm.core.Poi
import com.worldline.kmm.viewModels.logic.PoiDetailEvent
import com.worldline.kmm.viewModels.logic.PoiDetailState
import com.worldline.kmm.viewModels.logic.PoiDetailViewModel

@Composable
fun PoiDetailRoute(poiId: Long, navController: NavController) {
    val viewModel = remember { PoiDetailViewModel(poiId) }
    Text(text = "Hello $poiId")

    PoiDetailContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        navController = navController,
    )
}

@Composable
fun PoiDetailContent(
    state: PoiDetailState,
    navController: NavController,
    onEvent: (PoiDetailEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalle") },
                navigationIcon = run {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        },
        content = {
            when (state) {
                is PoiDetailState.InProgress -> LoadingView()
                is PoiDetailState.Error -> EmptyView()
                is PoiDetailState.Success -> PoiDetailSuccessView(state)
            }
        }
    )

    LaunchedEffect(Unit) {
        onEvent(PoiDetailEvent.Attach)
    }
}

@Composable
fun PoiDetailSuccessView(
    poiDetailState: PoiDetailState.Success
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(poiDetailState.poi.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = Dp.Infinity, height = 240.dp)
        )
        Text(
            text = poiDetailState.poi.title,
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.secondary),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun PoiDetailPreview() {

    val mockedPoi = Poi(
        id = 1,
        title = "Wanda Metropolitano",
        latitude = 41.403706,
        longitude = 2.173504,
        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Estadio_Wanda_Metropolitano_%282018%29.jpg/1200px-Estadio_Wanda_Metropolitano_%282018%29.jpg"
    )

    AppTheme {
        PoiDetailContent(state = PoiDetailState.Success(mockedPoi), rememberNavController()) {}
    }
}