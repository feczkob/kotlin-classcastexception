plugins {
    id("java")
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.allopen") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    id("io.quarkus")
    id("org.liquibase.gradle") version "2.2.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-resteasy-reactive")

    // Hibernate ORM specific dependencies
    implementation("io.quarkus:quarkus-hibernate-orm-panache-kotlin")
//    // JDBC driver dependencies
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-hibernate-orm")
    // Liquibase specific dependencies
    implementation("io.quarkus:quarkus-liquibase")
    // Include database drivers to be used by liquibase
    liquibaseRuntime("org.liquibase:liquibase-core:4.20.0")
    liquibaseRuntime("org.postgresql:postgresql:42.5.4")
    liquibaseRuntime("info.picocli:picocli:4.7.3")

    // mapper
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")

    // OpenAPI
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("io.quarkus:quarkus-hibernate-validator")

    // test
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "com.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    sourceSets {
        main {
            java {
                srcDirs("src/main/kotlin")
            }
        }
    }
}

kapt {
    includeCompileClasspath = false
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    systemProperty("native.image.path", "${project.buildDir}/${project.name}-runner")
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}