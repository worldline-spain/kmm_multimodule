package com.worldline.kmm.data.repository.di

import com.worldline.kmm.data.repository.logic.Repository
import com.worldline.kmm.data.repository.logic.SharedRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        SharedRepository(get(), get())
    }
}

