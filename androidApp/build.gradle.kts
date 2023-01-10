import Dependencies.Modules

plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {

    with(Dependencies.Shared.Ui) {
        implementation(coroutines)
    }

    with(Modules) {
        implementation(project(shared))
    }

    with(Dependencies.Android) {
        implementation(core)
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
        implementation(koinCompose)
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
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = "com.worldline.kmm.android"
        minSdkVersion(23)
        targetSdkVersion(Versions.compileSdkVersion)
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
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    packagingOptions {
        exclude("META-INF/licenses/**")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}