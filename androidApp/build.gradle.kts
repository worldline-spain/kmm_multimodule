import Dependencies.Modules
import Dependencies.Modules.Poi

plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {

    with(Modules) {
        implementation(project(core))

        with(Poi) {
            implementation(project(vm))
            implementation(project(repository))
            implementation(project(remote))
            implementation(project(local))
        }
    }

    with(Dependencies.Android) {
        implementation(coroutines)
    }

    with(Dependencies.DI) {
        implementation(koinCore)
        implementation(koinAndroid)
    }

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.worldline.nespresso.android"
        minSdkVersion(23)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
}