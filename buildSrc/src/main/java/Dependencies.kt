import org.gradle.api.Project
import java.text.SimpleDateFormat
import java.util.*

fun getVersionNumber(): Int {
    val dateFormat = SimpleDateFormat("yyMMddHH")
    val versionNumber = dateFormat.format(Date())
    return versionNumber.toInt()
}

fun Project.getLocalProperty(name: String): String {
    val localProperties = java.util.Properties()
    localProperties
        .load(rootProject.file("local.properties").inputStream())

    return localProperties.getOrElse(name) {
        throw IllegalArgumentException("Define $name in your local.properties file")
    } as String
}


object Versions {
    const val compileSdkVersion = 33
    const val kotlin = "1.7.20"
    val versionCode = getVersionNumber()
    const val versionName = "1.1.1"
    const val coroutines = "1.6.4"
    const val serialization = "1.2.2"
    const val ktor = "2.1.2"
    const val koin = "3.2.2"
    const val sqldelight = "1.5.2"
    const val coreKtx = "1.6.0"
    const val navigation = "2.3.5"
    const val klock = "2.4.1"
    const val multiplatformSettings = "0.8"
    const val arrow = "1.0.0"
    const val mapbox = "10.2.0"
    const val coil = "1.4.0"

    //Jetpack Compose
    const val compose = "1.2.1"
    const val composeCompiler = "1.3.2"
    const val navCompose = "2.5.2"
    const val accompanist = "0.26.4-beta"
    const val constraintLayout = "1.0.1"
    const val lifecycle = "2.5.0"
}

object Dependencies {

    object Modules {
        const val shared = ":shared:shared"
        const val core = ":shared:core"
        const val local = ":shared:data:local"
        const val remote = ":shared:data:remote"
        const val repository = ":shared:data:repository"
        const val ui = ":shared:views"
    }

    object Root {
        const val android = "com.android.tools.build:gradle:7.2.1"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
        const val cocoapods = "co.touchlab:kotlinnativecocoapods:0.6"
        const val sqldelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}"
        const val google = "com.google.gms:google-services:4.3.3"
    }

    object Android {
        const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val recycler = "androidx.recyclerview:recyclerview:1.2.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val mapbox = "com.mapbox.maps:android:${Versions.mapbox}"
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object DI {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:3.2.1"
    }

    object Compose {
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundationLayout =
            "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"

        const val coilCompose = "io.coil-kt:coil-compose:1.3.1"
        const val accompanistNavigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
        const val accompanistSwipeToRefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
    }

    object Shared {
        const val arrow = "io.arrow-kt:arrow-core:${Versions.arrow}"

        object Core {
            object Android {
                const val lifecycleViewModel =
                    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
            }

            object Native
        }

        object Ui {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

            object Android {
                const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
                const val navigationFragment =
                    "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            }

            object Native {

            }
        }

        object Data {
            object Local {
                const val sqldelight =
                    "com.squareup.sqldelight:runtime:${Versions.sqldelight}"

                object Android {
                    const val sqldelightDriver =
                        "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
                }

                object Native {
                    const val sqldelightDriver =
                        "com.squareup.sqldelight:native-driver:${Versions.sqldelight}"
                }
            }

            object Remote {
                const val serialization =
                    "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
                const val ktorClientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
                const val ktorClientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
                const val ktorClientContentNegotiation =
                    "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
                const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
                const val ktorClientSerializationJson =
                    "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
                const val ktorClientAuth = "io.ktor:ktor-client-auth:${Versions.ktor}"
                const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"

                object Android {
                    const val ktorClientCore = "io.ktor:ktor-client-android:${Versions.ktor}"
                }

                object Native {
                    const val ktorClientCore = "io.ktor:ktor-client-ios:${Versions.ktor}"
                }
            }
        }
    }
}