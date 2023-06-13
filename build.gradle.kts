
plugins {
    alias(libs.plugins.jvm)
    signing
    `maven-publish`
}

group = "moe.yka"
version = "0.1.0"

dependencies {
    implementation(libs.kotlin.stdlib)
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

            pom {
                name.set("tool")
                description.set("tool")
                inceptionYear.set("2023")
                url.set("https://github.com/yunkuangao/tool")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("yunkuangao")
                        name.set("yun")
                        url.set("https://github.com/yunkuangao/")
                    }
                }
                scm {
                    url.set("https://github.com/yunkuangao/tool/")
                    connection.set("scm:git:git://github.com/yunkuangao/tool.git")
                    developerConnection.set("scm:git:ssh://git@github.com/yunkuangao/tool.git")
                }
            }
        }
    }
    repositories {
        // Maven Central
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername")
                password = System.getenv("ORG_GRADLE_PROJECT_mavenCentralPassword")
            }
        }
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

signing {
    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications["maven"])
}