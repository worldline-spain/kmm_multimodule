package com.worldline.kmm.feature.repository.di

import com.worldline.kmm.feature.repository.logic.Repository
import com.worldline.kmm.feature.repository.logic.SharedRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        SharedRepository(get(), get())
    }
}

