# Kotlin multiplaform sample with multiple modules

## Introduction

Hello adn welcome! This is our approach about how to implement a kotlin multiplatform Architecture.
This repo is still Work-In-Progess but for sure you can just take a look!

## Project structure

The project is based on 3 main "folders" but lot of modules, let us explain:

`androidApp`: This includes the activities/fragments needed to paint the UI
`iosApp`: This includes the `SwiftUI` views
`shared`: Includes all the `shared` submodules.

## How a feature looks like?

One feature will involve at least 5 modules. For example, the `Poi list screen` contains these:

- `androidApp`/`iosApp`: `MainActivity` and `ContentView`
- `shared:feature:poi:poiui`: `PoiUI`
- `shared:feature:poi:poirepository`: `PoiRepository`
- `shared:data:remote`: `PoiRemote`
- `shared:data:local`: `PoiLocal`
- `shared:core`: `core` data class...

### Module diagram

Take a look to this diagram, just to have a project picture:

![alt text](https://github.com/worldline-spain/kmm_multimodule/blob/master/art/diagram.jpeg?raw=true "Diagram")

## Technologies/libraries used:

- `Coroutines`: https://kotlinlang.org/docs/coroutines-overview.html
- `StateFlow`: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/
- `SQLDelight`: https://cashapp.github.io/sqldelight/multiplatform_sqlite/
- `Ktor`: https://ktor.io/docs/client.html
- `Koin`: https://insert-koin.io/

WIP
