# Kotlin multiplaform sample with multiple modules

## Introduction

Hello and welcome! This is our approach to implement a kotlin multiplatform Architecture.
This repo is still Work-In-Progess but for sure you can just take a look!

## Project structure

The project is based on only 3 main "folders" but lots of modules. The folders are:

`androidApp`: This includes the activities/fragments needed to paint the UI
`iosApp`: This includes the `SwiftUI` views
`shared`: Includes all the `shared` submodules.

## What does a feature look like?

A feature involves at least 5 modules. For example, the `Poi list screen` contains:

- `androidApp`/`iosApp`: `MainActivity` and `ContentView`
- `shared:feature:poi:poiui`: `PoiUI`
- `shared:feature:poi:poirepository`: `PoiRepository`
- `shared:data:remote`: `PoiRemote`
- `shared:data:local`: `PoiLocal`
- `shared:core`: `core` data classes

### Module diagram

Take a look at this diagram, just to have a picture of the project:

![Diagram](https://github.com/worldline-spain/kmm_multimodule/blob/master/art/diagram.jpeg?raw=true "Diagram")

## Technologies/libraries used:

- `Coroutines`: https://kotlinlang.org/docs/coroutines-overview.html
- `StateFlow`: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/
- `SQLDelight`: https://cashapp.github.io/sqldelight/multiplatform_sqlite/
- `Ktor`: https://ktor.io/docs/client.html
- `Koin`: https://insert-koin.io/

WIP
