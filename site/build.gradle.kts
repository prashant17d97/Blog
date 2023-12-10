import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
//    alias(libs.plugins.serialization.plugin)
    kotlin("plugin.serialization") version "1.9.21"

}

group = "com.prashant.blog"
version = "1.0-SNAPSHOT"


kobweb {
    app {
        index {
            head.add {

                //External JavaScripts
                script {
                    src =
                        "https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"
                }
                script {
                    src = "/scripts/devicetype.js"
                }
                script {
                    src =
                        "https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                }
                script {
                    src = "/highlight.min.js"
                }

                //External CSS
                link {
                    rel = "stylesheet"
                    href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                }

                link {
                    rel = "stylesheet"
                    href = "/github-dark.css"
                }
                link {
                    rel = "stylesheet"
                    href =
                        "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css"
                }
                link(
                    rel = "stylesheet",
                    href = "https://fonts.googleapis.com/css?family=Playfair+Display",
                    type = "text/css"
                )

            }
            description.set("Debug Desk")
        }
    }
}

kotlin {

    js(IR) { // or: LEGACY, BOTH
        // ...
        binaries.executable() // not applicable to BOTH, see details below
    }
    configAsKobwebApplication("blog", includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.silk.icons.fa)
                implementation(libs.kobwebx.markdown)
//                implementation(libs.kotlinx.serialization)
                implementation(libs.gson)

//                implementation(npm("@js-joda/timezone", "2.3.0"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
//                implementation(libs.kotlinx.serialization)
                implementation(libs.kmongo.database.driver)
            }
        }
    }
}

