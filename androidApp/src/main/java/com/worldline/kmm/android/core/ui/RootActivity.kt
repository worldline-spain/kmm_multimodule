package com.worldline.kmm.android.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.worldline.kmm.android.theme.AppTheme

abstract class RootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                BuildUI()
            }
        }
    }

    @Composable
    abstract fun BuildUI()
}