plugins {
    alias(libs.plugins.jvm)
    `maven-publish`
    `java-library`
    alias(libs.plugins.dokka)
    signing
}

group = "moe.yka"
version = "0.1.0"

repositories {
    mavenCentral()
}

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
        // Maven Central
//        maven {
//            name = "OSSRH"
//            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//            credentials {
//                username = System.getenv("ORG_GRADLE_PROJECT_sonatypeUsername")
//                password = System.getenv("ORG_GRADLE_PROJECT_sonatypePassword")
//            }
//        }
        // GitHub Release
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/yunkuangao/tool")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}