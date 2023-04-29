package dependencies

object Deps {

    object AndroidX {

        const val kotlin = "androidx.core:core-ktx:1.7.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"

        object Activity {
            const val compose = "androidx.activity:activity-compose:1.3.1"
        }

        object Compose {
            const val version = "1.1.1"

            object UI {
                const val ui = "androidx.compose.ui:ui:$version"
                const val tooling = "androidx.compose.ui:ui-tooling:$version"
                const val tooling_preview = "androidx.compose.ui:ui-tooling-preview:$version"
                const val jUnit = "androidx.compose.ui:ui-test-junit4:$version"
                const val manifest = "androidx.compose.ui:ui-test-manifest:$version"
            }

            const val material = "androidx.compose.material3:material3:1.0.0-alpha02"

        }

        object Test {
            const val jUnit = "androidx.test.ext:junit:1.1.5"
            const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        }
    }

    object JUnit {
        const val jUnit = "junit:junit:4.13.2"
    }
}