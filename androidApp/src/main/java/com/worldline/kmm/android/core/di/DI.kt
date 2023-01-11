package com.worldline.kmm.android.core.di

import com.worldline.kmm.views.logic.HomeViewModel
import com.worldline.kmm.views.logic.PoiDetailViewModel
import com.worldline.kmm.views.logic.PoiListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel().attach() }
    viewModel { PoiListViewModel().attach() }
    viewModel { (poiId: Long) ->
        PoiDetailViewModel(
            poiId = poiId
        ).attach()
    }
}