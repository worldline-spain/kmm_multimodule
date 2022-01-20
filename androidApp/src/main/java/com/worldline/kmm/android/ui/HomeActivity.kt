package com.worldline.kmm.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.worldline.kmm.android.core.navigation.PoiApp
import com.worldline.kmm.android.core.ui.RootActivity
import com.worldline.kmm.android.theme.AppTheme

class HomeActivity : RootActivity() {
    @ExperimentalComposeUiApi
    @Composable
    override fun BuildUI() {
        AppTheme {
            PoiApp()
        }
    }
}
