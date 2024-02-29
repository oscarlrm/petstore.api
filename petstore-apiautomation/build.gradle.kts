plugins {
    id("java")
    id("com.github.spacialcircumstances.gradle-cucumber-reporting") version "0.1.25"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.apache.httpcomponents:httpcore:4.4.13")
    implementation("io.cucumber:cucumber-java:7.2.1")
    implementation("io.cucumber:cucumber-junit:7.2.1")
    implementation("io.rest-assured:rest-assured:4.4.0")
    testImplementation("io.rest-assured:rest-assured:4.4.0")
    testImplementation("io.rest-assured:json-schema-validator:4.4.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}



cucumberReports {
    outputDir = file("build/reports/")
    buildId = "0"
    reports = files("build/cucumber-reports/cucumber.json")
    testTasksFinalizedByReport = false
}

