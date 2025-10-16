plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // Plugin de serialización necesario para kotlinx-serialization y argumentos serializables.
    kotlin("plugin.serialization") version "2.0.0" apply false
}