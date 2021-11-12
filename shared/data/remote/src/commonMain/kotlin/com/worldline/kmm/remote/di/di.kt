package com.worldline.kmm.remote.di

import com.worldline.kmm.remote.PoiRemote
import com.worldline.kmm.remote.SharedPoiRemote
import org.koin.dsl.module

val remoteModule = module {
    single<PoiRemote> {
        SharedPoiRemote()
    }
}