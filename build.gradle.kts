group = "com.firi.sdk.FiriSDK"
version = "1.0.0"

kotlin {
    explicitApi()
}

plugins {
    kotlin("jvm") version "2.1.0"
    id("maven-publish")
    id("signing")
}

val ktorVersion: String by project
val junitJupiterVersion: String by project
val slf4jSimpleVersion: String by project

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jSimpleVersion")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("skipped", "failed")
            showStackTraces = true
            showExceptions = true
            showCauses = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenKotlin") {
            from(components["kotlin"])

            // Customize POM metadata
            pom {
                name.set("Firi-SDK")
                description.set("Welcome to the Firi SDK for Kotlin, an easy-to-use SDK for interacting with the Firi cryptocurrency trading platform. This SDK allows developers to integrate Firi's functionality into their Kotlin-based applications with minimal effort.")
                url.set("https://github.com/echokrist/Firi-SDK-Kotlin")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/echokrist/Firi-SDK-Kotlin/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("echokrist")
                        name.set("echokrist")
                        email.set("echokrist@pm.me")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/echokrist/Firi-SDK-Kotlin")
                    developerConnection.set("scm:git:ssh://git@github.com:echokrist/Firi-SDK-Kotlin.git")
                    url.set("https://github.com/echokrist/Firi-SDK-Kotlin")
                }
            }
        }
    }

    repositories {
        maven {
            name = "MavenCentral"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_SIGNING_KEY_PASSWORD")
    )
    sign(publishing.publications["mavenKotlin"])
}
