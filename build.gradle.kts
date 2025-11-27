plugins {
    id("org.jetbrains.intellij") version "1.17.3"
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = "org.jetbrains.webstorm"
version = "1.5-gc"

repositories {
    mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2023.3.4")
    type.set("IU")
}

sourceSets {
    main {
        java.srcDirs("src/main/gen")
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    test {
        systemProperty("idea.home.path", "\$HOME/IdeaProjects")
    }

    patchPluginXml {
        sinceBuild.set("223")
        untilBuild.set("253.*")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
