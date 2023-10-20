plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                // Kotlinx
                with(Dependencies.Kotlinx) {
                    implementation(coroutines)
                }

                // Ktor
                with(Dependencies.Ktor) {
                    implementation(core)
                    implementation(contentNegotiation)
                    implementation(json)
                }

                // Ballast
                with(Dependencies.Ballast) {
                    implementation(core)
                    implementation(navigation)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.activity.compose)
                api(libs.appcompat)
                api(libs.core.ktx)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.prasahnt.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

object Dependencies {
    object Kotlinx {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlinx.kotlinxVersion}"
    }

    object Compose {
        const val navigation =
            "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

    }

    object Preview {
        const val preview = "org.jetbrains.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.Ktor.ktorVersion}"
        const val cio = "io.ktor:ktor-client-cio:${Versions.Ktor.ktorVersion}"
        const val contentNegotiation =
            "io.ktor:ktor-client-content-negotiation:${Versions.Ktor.ktorVersion}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.Ktor.ktorVersion}"
        const val darwin = "io.ktor:ktor-client-darwin:${Versions.Ktor.ktorVersion}"
    }

    object Ballast {
        const val core = "io.github.copper-leaf:ballast-core:${Versions.Ballast.ballastVersion}"
        const val navigation =
            "io.github.copper-leaf:ballast-navigation:${Versions.Ballast.ballastVersion}"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:${Versions.Coil.coilVersion}"
    }

    // MARK: - Versions -
    private object Versions {
        object Ktor {
            const val ktorVersion = "2.1.3"
        }

        object Kotlinx {
            const val kotlinxVersion = "1.6.4"
        }

        object Ballast {
            const val ballastVersion = "2.3.0"
        }

        object Coil {
            const val coilVersion = "2.2.2"
        }

        const val composeVersion = "1.5.1"
        const val composeNavigation = "2.7.2"

    }
}