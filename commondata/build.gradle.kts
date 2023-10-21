import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kobweb.library)
    alias(libs.plugins.serialization.plugin)
}


kotlin {
    configAsKobwebLibrary(includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.serialization)
            }
        }
    }
}
