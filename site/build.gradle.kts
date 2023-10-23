import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.serialization.plugin)
}

group = "com.prashant.blog"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            head.add {
                script {
                    src = "https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"
                }
                script {
                    src = "/scripts/devicetype.js"
                }
                link(
                    rel = "stylesheet",
                    href = "https://fonts.googleapis.com/css?family=Playfair+Display",
                    type = "text/css"
                )

                link(
                    rel = "stylesheet",
                    href = "/css/styles.css",
                    type = "text/css"
                )
            }
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("blog", includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.ktor.serialization)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.silk.icons.fa)
                implementation(libs.kobwebx.markdown)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
            }
        }
    }
}
