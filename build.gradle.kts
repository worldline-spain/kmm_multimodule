buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        with(Dependencies.Root) {
            classpath(android)
            classpath(serialization)
            classpath(cocoapods)
            classpath(sqldelight)
            classpath(google)
        }

        classpath(kotlin("gradle-plugin", Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                val MAPBOX_DOWNLOADS_TOKEN: String by project
                password = MAPBOX_DOWNLOADS_TOKEN
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

abstract class KmmModuleGeneratorTask : DefaultTask() {

    @get:Input
    @set:Option(option = "moduleName", description = "The name of the module you want to create")
    var moduleName: String = ""

    @get:Input
    @set:Option(option = "modulePath", description = "The root path for the new module")
    var modulePath: String = ""

    @get:Input
    @Option(option = "packageName", description = "The packageName")
    var packageName: String = ""


    private fun fromPackageNameToPath(packageName: String) =
        packageName.replace(".", "/")

    private fun createManifestFile(packageName: String) = """
            <?xml version="1.0" encoding="utf-8"?>
            <manifest package="$packageName" />
        """.trimIndent()

    private fun createBuildGradleKtsFile(name: String) = """
            import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
            plugins {
                kotlin("multiplatform")
                kotlin("native.cocoapods")
                id("com.android.library")
            }
            version = "1.0"
            kotlin {
                android()
                iosX64()
                iosArm64()
                iosSimulatorArm64()
                cocoapods {
                    summary = TODO("Define the module Summary")
                    homepage = TODO("Define the module homepage")
                    ios.deploymentTarget = TODO("Define the module homepage")
                    framework {
                        baseName = "$name"
                        isStatic = false
                        linkerOpts.add("-lsqlite3")
                    }
                    podfile = project.file(TODO("Podifile Path"))
                }
                sourceSets {
                    val commonMain by getting { dependencies {} }
                    val commonTest by getting { dependencies {} }
                    val androidMain by getting { dependencies {} }
                    val androidTest by getting { dependencies {} }
                    val iosX64Main by getting
                    val iosArm64Main by getting
                    val iosSimulatorArm64Main by getting
                    val iosMain by creating {
                        dependsOn(commonMain)
                        iosX64Main.dependsOn(this)
                        iosArm64Main.dependsOn(this)
                        iosSimulatorArm64Main.dependsOn(this)
                        dependencies {}
                    }
                    val iosX64Test by getting
                    val iosArm64Test by getting
                    val iosSimulatorArm64Test by getting
                    val iosTest by creating {
                        dependsOn(commonTest)
                        iosX64Test.dependsOn(this)
                        iosArm64Test.dependsOn(this)
                        iosSimulatorArm64Test.dependsOn(this)
                        dependencies {}
                    }
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

        """.trimIndent()

    private fun sourceSet(basePath: String, variant: String) =
        "$basePath/src/$variant/kotlin/${fromPackageNameToPath(packageName)}/$moduleName"

    @TaskAction
    fun doWork() {
        val basePath = "${project.projectDir}/$modulePath/$moduleName/"
        println("Creating module $moduleName")
        println("Creating commonMain...")
        project.mkdir(File(sourceSet(basePath, "commonMain")))
        println("Creating androidMain...")
        project.mkdir(File(sourceSet(basePath, "androidMain")))
        println("Creating iosMain...")
        project.mkdir(File(sourceSet(basePath, "iosMain")))

        println("Creating AndroidManifest...")
        val manifest = File("$basePath/src/androidMain/AndroidManifest.xml")
        manifest.createNewFile()
        manifest.writeText(createManifestFile(packageName))

        println("Creating build.gradle.kts file...")
        val gradleKts = File("$basePath/build.gradle.kts")
        gradleKts.createNewFile()
        gradleKts.writeText(createBuildGradleKtsFile(moduleName))

        println("Everything is done!")
    }
}

open class KmmModuleGeneratorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register<KmmModuleGeneratorTask>("createKmmModule")
    }

}

apply<KmmModuleGeneratorPlugin>()