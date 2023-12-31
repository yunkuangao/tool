rootProject.name = "tool"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {

    versionCatalogs {

        repositories {
            mavenLocal()
            google()
            gradlePluginPortal()
            mavenCentral()
        }

        create("libs") {

            val kotlinVersion = extra["kotlinVersion"] as String
            val publishVersion = extra["publishVersion"] as String

            // version
            version("kotlin", kotlinVersion)
            version("publish", publishVersion)

            // plugin
            plugin("jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
            plugin("publish", "com.vanniktech.maven.publish").versionRef("publish")

            // dependency
            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlin")
        }
    }
}
