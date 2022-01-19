package com.worldline.kmm.android.ui.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.worldline.kmm.android.core.navigation.PoiApp
import com.worldline.kmm.android.core.ui.RootActivity

class HomeActivity : RootActivity() {

    companion object {
        fun intent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    @ExperimentalComposeUiApi
    @Composable
    override fun BuildUI() {
        PoiApp()
    }
}
