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
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
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
                val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
                    System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
                    System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
                    else -> ::iosX64
                }
                iosTarget("ios") {}
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
                    val iosMain by getting { dependencies {} }
                    val iosTest by getting { dependencies {} }
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