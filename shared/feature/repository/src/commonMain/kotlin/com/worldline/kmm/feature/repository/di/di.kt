package com.worldline.kmm.feature.repository.di

import com.worldline.kmm.feature.repository.Repository
import com.worldline.kmm.feature.repository.SharedRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        SharedRepository(get(), get())
    }
}

