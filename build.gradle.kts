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

tasks.register<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generateWebAssemblyLexer") {
    sourceFile.set(file("src/main/grammars/WebAssemblyLexer.flex"))
    targetOutputDir.set(file("src/main/gen/com/intellij/webassembly/lang/lexer"))
    purgeOldFiles.set(true)
}

tasks.register<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generateWebAssemblyParser") {
    sourceFile.set(file("src/main/grammars/WebAssemblyParser.bnf"))
    targetRootOutputDir.set(file("src/main/gen"))
    pathToParser.set("/com/intellij/webassembly/lang/parser/WebAssemblyParser.java")
    pathToPsiRoot.set("/org/jetbrains/webstorm/lang/psi")
    purgeOldFiles.set(true)
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-proc:none")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    compileJava {
        dependsOn("generateWebAssemblyLexer", "generateWebAssemblyParser")
    }

    // Ensure generated files exist before Kotlin compilation
    named("compileKotlin") {
        dependsOn("generateWebAssemblyLexer", "generateWebAssemblyParser")
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
