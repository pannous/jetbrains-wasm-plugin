#!/bin/bash

# Auto-install WebAssembly GC Plugin to all IntelliJ-based IDEs

set -e

echo "🚀 Installing WebAssembly GC Plugin..."

# Build the plugin first
echo "🔨 Building plugin..."
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
./gradlew buildPlugin

# Find the built plugin
PLUGIN_ZIP="build/distributions/intellij-webassembly-plugin-1.5-gc.zip"

if [ ! -f "$PLUGIN_ZIP" ]; then
    echo "❌ Error: Plugin not found at $PLUGIN_ZIP"
    echo "Build may have failed"
    exit 1
fi

echo "✅ Plugin built successfully: $PLUGIN_ZIP"

# Find all IntelliJ-based IDE plugin directories
PLUGIN_DIRS=(
    "$HOME/Library/Application Support/JetBrains/IntelliJIdea2023.3/plugins"
    "$HOME/Library/Application Support/JetBrains/IntelliJIdea2024.1/plugins"
    "$HOME/Library/Application Support/JetBrains/IntelliJIdea2024.2/plugins"
    "$HOME/Library/Application Support/JetBrains/IntelliJIdea2024.3/plugins"
    "$HOME/Library/Application Support/JetBrains/IdeaIC2023.3/plugins"
    "$HOME/Library/Application Support/JetBrains/IdeaIC2024.1/plugins"
    "$HOME/Library/Application Support/JetBrains/IdeaIC2024.2/plugins"
    "$HOME/Library/Application Support/JetBrains/IdeaIC2024.3/plugins"
    "$HOME/Library/Application Support/JetBrains/CLion2023.3/plugins"
    "$HOME/Library/Application Support/JetBrains/CLion2024.1/plugins"
    "$HOME/Library/Application Support/JetBrains/CLion2024.2/plugins"
    "$HOME/Library/Application Support/JetBrains/CLion2024.3/plugins"
    "$HOME/Library/Application Support/JetBrains/RustRover2023.3/plugins"
    "$HOME/Library/Application Support/JetBrains/RustRover2024.1/plugins"
    "$HOME/Library/Application Support/JetBrains/RustRover2024.2/plugins"
    "$HOME/Library/Application Support/JetBrains/RustRover2024.3/plugins"
)

# Auto-detect installed IDEs
FOUND_DIRS=()
for dir in "${PLUGIN_DIRS[@]}"; do
    if [ -d "$(dirname "$dir")" ]; then
        FOUND_DIRS+=("$dir")
    fi
done

if [ ${#FOUND_DIRS[@]} -eq 0 ]; then
    echo "❌ No JetBrains IDE installations found"
    echo "Plugin is available at: $PLUGIN_ZIP"
    echo "Install manually via: Settings → Plugins → ⚙️ → Install Plugin from Disk"
    exit 1
fi

echo "📦 Found ${#FOUND_DIRS[@]} JetBrains IDE installation(s)"

# Install to each found IDE
for plugin_dir in "${FOUND_DIRS[@]}"; do
    ide_name=$(echo "$plugin_dir" | sed 's|.*/JetBrains/||' | sed 's|/plugins||')
    echo "📥 Installing to $ide_name..."

    # Create plugins directory if it doesn't exist
    mkdir -p "$plugin_dir"

    # Remove old installation if exists
    if [ -d "$plugin_dir/intellij-webassembly-plugin" ]; then
        echo "   Removing old installation..."
        rm -rf "$plugin_dir/intellij-webassembly-plugin"
    fi
    # Also remove legacy name if exists
    if [ -d "$plugin_dir/web-assembly-plugin" ]; then
        echo "   Removing legacy installation..."
        rm -rf "$plugin_dir/web-assembly-plugin"
    fi

    # Unzip plugin to plugins directory
    echo "   Extracting plugin..."
    unzip -q "$PLUGIN_ZIP" -d "$plugin_dir/"

    echo "   ✅ Installed to $ide_name"
done

echo ""
echo "✨ Installation complete!"
echo ""
echo "🔄 Restarting JetBrains IDEs..."

# Kill running JetBrains IDEs
pkill -f "CLion" 2>/dev/null || true
pkill -f "RustRover" 2>/dev/null || true
pkill -f "IntelliJ IDEA" 2>/dev/null || true
sleep 2

# Restart CLion if it exists
if [ -d "/Applications/CLion.app" ]; then
    echo "   Starting CLion..."
    open -a "CLion" 2>/dev/null || echo "   ℹ️  CLion failed to start"
fi

# Restart RustRover if it exists
if [ -d "/Applications/RustRover.app" ]; then
    echo "   Starting RustRover..."
    open -a "RustRover" 2>/dev/null || echo "   ℹ️  RustRover failed to start"
fi

echo ""
echo "📋 Next steps:"
echo "   1. Your JetBrains IDEs should restart automatically"
echo "   2. The WebAssembly GC plugin should now be active"
echo "   3. Open any .wat/.wast file to test GC syntax highlighting"
echo ""
echo "To verify installation:"
echo "   Settings → Plugins → Installed → search for 'WebAssembly'"
