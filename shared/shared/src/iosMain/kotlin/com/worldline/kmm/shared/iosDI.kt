package com.worldline.kmm.shared

import com.worldline.kmm.local.settings.Settings
import org.koin.dsl.module


fun initKoinIos(settings: Settings) {
    initKoin(
        module {
            single { settings }
        }
    )
}