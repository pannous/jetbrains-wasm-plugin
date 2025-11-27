#!/bin/bash
# Workaround build script for Kotlin/Java joint compilation issue
# Forces Java compilation before Kotlin sees the generated sources

set -e

export JAVA_HOME=$(/usr/libexec/java_home -v 17)

echo "🔨 Building IntelliJ WebAssembly Plugin..."
echo ""

# Step 1: Clean and compile Java (including generated sources)
echo "Step 1: Compiling Java sources..."
./gradlew clean compileJava

# Step 2: Now build everything (Kotlin can see compiled Java classes)
echo "Step 2: Building full plugin..."
./gradlew buildPlugin

echo ""
echo "✅ Build complete!"
echo "📦 Plugin: build/distributions/intellij-webassembly-plugin-1.5-gc.zip"
