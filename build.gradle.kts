import org.jetbrains.grammarkit.tasks.GenerateParserTask
import java.util.Properties

plugins {
    id("org.jetbrains.intellij") version "1.17.3"
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

// Auto-increment version on each build
val versionPropsFile = file("version.properties")
val versionProps = Properties().apply {
    if (versionPropsFile.exists()) {
        versionPropsFile.inputStream().use { load(it) }
    }
}

val major = versionProps.getProperty("major", "1").toInt()
val minor = versionProps.getProperty("minor", "5").toInt()
val patch = versionProps.getProperty("patch", "0").toInt()
val suffix = versionProps.getProperty("suffix", "gc")

// Increment patch version
val newPatch = patch + 1
versionProps.setProperty("patch", newPatch.toString())
versionPropsFile.writer().use { versionProps.store(it, "Auto-incremented by build") }

group = "org.jetbrains.webstorm"
version = "$major.$minor.$newPatch-$suffix"

repositories {
    mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2023.3.4")
    type.set("IC")  // Use Community edition - much smaller download
}

sourceSets {
    main {
        java.srcDirs("src/main/gen", "src/main/java")
        kotlin.srcDirs("src/main/kotlin")
    }
}

// Configure Grammar-Kit parser generation
tasks.register<GenerateParserTask>("generateWebAssemblyParser") {
    sourceFile.set(file("src/main/grammars/WebAssemblyParser.bnf"))
    targetRootOutputDir.set(file("src/main/gen"))
    pathToParser.set("com/intellij/webassembly/lang/parser/WebAssemblyParser.java")
    pathToPsiRoot.set("com/intellij/webassembly/lang/psi")
    purgeOldFiles.set(false)  // Don't purge - only update parser
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    compileJava {
        options.release.set(17)
        // Java compilation needs to see the compiled Kotlin classes
        dependsOn(compileKotlin)
        classpath += files(compileKotlin.get().destinationDirectory)
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    test {
        // Use a temp directory for tests instead of a non-existent IdeaProjects directory
        val tempDir = File(System.getProperty("java.io.tmpdir"), "idea-test-home")
        tempDir.mkdirs()
        systemProperty("idea.home.path", tempDir.absolutePath)
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
