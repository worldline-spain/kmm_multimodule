package com.worldline.kmm.android.core.di

import com.worldline.kmm.feature.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel().attach() }
}