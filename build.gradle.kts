plugins {
    id("groovy")
    id("org.gradlex.internal.plugin-publish-conventions") version "0.4"
}

group = "org.gradlex"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    testImplementation("org.gradle.exemplar:samples-check:1.0.0")
}

pluginPublishConventions {
    id("${project.group}.${project.name}")
    implementationClass("org.gradlex.javamodule.testing.JavaModuleTestingPlugin")
    displayName("Java Module Testing Gradle Plugin")
    description("A plugin to test Java Modules (whitebox and blackbox) without the hassle.")
    tags("gradlex", "java", "modularity", "jigsaw", "jpms", "testing")
    gitHub("https://github.com/gradlex-org/java-module-testing")
    developer {
        id.set("jjohannes")
        name.set("Jendrik Johannes")
        email.set("jendrik@gradlex.org")
    }
}

tasks.test {
    inputs.dir(layout.projectDirectory.dir("samples"))
}
