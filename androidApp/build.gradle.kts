import Dependencies.Modules
import Dependencies.Modules.Home
import Dependencies.Modules.Poi

plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {

    with(Modules) {
        implementation(project(core))

        with(Poi) {
            implementation(project(ui))
            implementation(project(repository))
            implementation(project(remote))
            implementation(project(local))
        }

        with(Home) {
            implementation(project(ui))
        }
    }

    with(Dependencies.Android) {
        implementation(core)
        implementation(coroutines)
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(navigationFragment)
        implementation(mapbox)
        implementation(coil) {
            exclude(group = "org.jetbrains.kotlinx")
        }
    }

    with(Dependencies.DI) {
        implementation(koinCore)
        implementation(koinAndroid)
    }

    with(Dependencies.Compose) {
        implementation(ui)
        implementation(uiGraphics)
        implementation(foundationLayout)
        implementation(material)
        implementation(navigation)
        implementation(coilCompose)
        implementation(accompanistNavigationAnimation)
        implementation(accompanistSwipeToRefresh)
        implementation(uiTooling)
        implementation(constraintLayout)
        implementation(lifecycle)
    }
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.worldline.kmm.android"
        minSdkVersion(23)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"

        resValue(
            "string",
            "mapbox_access_token",
            System.getenv("MAPBOX_API_KEY") ?: getLocalProperty("MAPBOX_API_KEY")
        )
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        exclude("META-INF/licenses/**")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}