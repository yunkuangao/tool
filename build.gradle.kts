plugins {
    alias(libs.plugins.jvm)
    `maven-publish`
    `java-library`
    alias(libs.plugins.dokka)
    signing
}

group = "moe.yka"
version = "0.1.0"

dependencies {
    implementation(libs.kotlin.stdlib)

    testImplementation(testLibs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// publish maven repo
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "tool"
            version = System.getenv("VERSION")
            from(components["java"])
        }
    }
    repositories {
        // GitHub Release
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/yunkuangao/tool")
            credentials {
                username = project.property("GitHubPackagesUsername").toString()
                password = project.property("GitHubPackagesPassword").toString()
            }
        }
    }
}