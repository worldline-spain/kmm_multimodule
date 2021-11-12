import java.text.SimpleDateFormat
import java.util.*

fun getVersionNumber(): Int {
    val dateFormat = SimpleDateFormat("yyMMddHH")
    val versionNumber = dateFormat.format(Date())
    return versionNumber.toInt()
}

object Versions {
    const val kotlin = "1.5.30"
    val versionCode = getVersionNumber()
    const val versionName = "1.1.1"
    const val coroutines = "1.5.2-native-mt"
    const val serialization = "1.2.2"
    const val ktor = "1.6.4"
    const val koin = "3.1.2"
    const val sqldelight = "1.5.0"
    const val coreKtx = "1.6.0"
    const val navigation = "2.3.5"
    const val klock = "2.4.1"
    const val multiplatformSettings = "0.8"
    const val arrow = "1.0.0"

    //Jetpack Compose
    const val compose = "1.0.3"
    const val navCompose = "2.4.0-alpha08"
    const val accompanist = "0.17.0"
    const val constraintLayout = "1.0.0-beta02"
}

object Dependencies {

    object Modules {

        const val core = ":shared:core"

        object Poi {
            const val vm = ":shared:ui:logic:poilistvm"
            const val repository = ":shared:feature:poi"
            const val remote = ":shared:data:remote"
            const val local = ":shared:data:local"
        }
    }

    object Root {
        const val android = "com.android.tools.build:gradle:7.0.2"
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
        const val coroutines = Shared.Ui.Android.coroutines
    }

    object DI {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Compose {
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
        const val accompanistSwipeToRefresh =  "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
    }

    object Shared {
        const val arrow = "io.arrow-kt:arrow-core:${Versions.arrow}"

        object Core

        object Ui {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

            object Android {
                const val coroutines =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
                const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
                const val navigationFragment =
                    "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            }

            object Native {
                const val coroutines =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
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
                const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
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