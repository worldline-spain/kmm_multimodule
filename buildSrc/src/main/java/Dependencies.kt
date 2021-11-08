import java.text.SimpleDateFormat
import java.util.*

fun getVersionNumber(): Int {
    val dateFormat = SimpleDateFormat("yyMMddHH")
    val versionNumber = dateFormat.format(Date())
    return versionNumber.toInt()
}

const val dexPath = "dexguard"

object Versions {
    const val kotlin = "1.5.31"
    val versionCode = getVersionNumber()
    const val versionName = "1.1.1"
    const val coroutines = "1.5.2-native-mt"
    const val serialization = "1.0.1"
    const val ktor = "1.6.4"
    const val koin = "3.1.2"
    const val sqldelight = "1.5.2"
    const val arrow = "1.0.0"
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
        const val android = "com.android.tools.build:gradle:4.2.2"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
        const val cocoapods = "co.touchlab:kotlinnativecocoapods:0.6"
        const val sqldelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}"
        const val google = "com.google.gms:google-services:4.3.3"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.1.0"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val recycler = "androidx.recyclerview:recyclerview:1.2.0-alpha01"
        const val material = "com.google.android.material:material:1.2.0-alpha03"
        const val coroutines = Shared.Ui.Android.coroutines
    }

    object DI {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
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