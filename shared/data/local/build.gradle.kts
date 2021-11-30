import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.squareup.sqldelight")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Local module"
        homepage = "Local module"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "local"
            isStatic = false
        }
        podfile = project.file("../../../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Dependencies.Modules.core))

                with(Dependencies.Shared.Data.Local) {
                    implementation(sqldelight)
                }
                with(Dependencies.DI) {
                    implementation(koinCore)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                with(Dependencies.Shared.Data.Local.Android) {
                    implementation(sqldelightDriver)
                }
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                with(Dependencies.Shared.Data.Local.Native) {
                    implementation(sqldelightDriver)
                }
            }
        }
        val iosTest by getting
    }
}

sqldelight {
    database("kmm") {
        packageName = "com.worldline.kmm.db"
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(31)
    }
}