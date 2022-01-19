import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
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
        summary = "Home ViewModel module"
        homepage = "Home ViewModel module"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "HomeUI"
            isStatic = false
            linkerOpts.add("-lsqlite3")
        }
        podfile = project.file("../../../../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Dependencies.Shared.Ui) {
                    implementation(coroutines) {
                        version {
                            strictly(Versions.coroutines)
                        }
                    }
                }
                with(Dependencies.DI) {
                    implementation(koinCore)
                    implementation(koinAndroid)
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
                with(Dependencies.Shared.Ui.Android) {
                    implementation(coroutines)
                }
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
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