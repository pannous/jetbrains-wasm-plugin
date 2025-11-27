#!/bin/bash

# Auto-install WebAssembly GC Plugin to all IntelliJ-based IDEs

set -e

echo "🚀 Installing WebAssembly GC Plugin..."

# Build the plugin using build.sh wrapper (handles Kotlin/Java compilation order)
./build.sh

# Find the built plugin
PLUGIN_ZIP="build/distributions/intellij-webassembly-plugin-1.5-gc.zip"

if [ ! -f "$PLUGIN_ZIP" ]; then
    echo "❌ Error: Plugin not found at $PLUGIN_ZIP"
    echo "Build may have failed"
    exit 1
fi

echo "✅ Plugin built successfully: $PLUGIN_ZIP"

# Auto-detect all JetBrains IDE installations
JETBRAINS_BASE="$HOME/Library/Application Support/JetBrains"
FOUND_DIRS=()

if [ -d "$JETBRAINS_BASE" ]; then
    # Find all IDE directories (IntelliJ, CLion, RustRover, etc.)
    for ide_dir in "$JETBRAINS_BASE"/*; do
        if [ -d "$ide_dir" ]; then
            plugin_dir="$ide_dir/plugins"
            # Check if this looks like a real IDE installation (has config or plugins)
            if [ -d "$ide_dir" ]; then
                FOUND_DIRS+=("$plugin_dir")
            fi
        fi
    done
fi

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

# Kill all running JetBrains IDEs to ensure plugin is loaded
pkill -f "RustRover" 2>/dev/null || true
pkill -f "CLion" 2>/dev/null || true
pkill -f "IntelliJ IDEA" 2>/dev/null || true
pkill -f "WebStorm" 2>/dev/null || true
pkill -f "Rider" 2>/dev/null || true
sleep 2

# Restart RustRover if it exists
if [ -d "/Applications/RustRover.app" ]; then
    echo "   Starting RustRover..."
    open -a "RustRover" 2>/dev/null || echo "   ℹ️  RustRover failed to start"
fi

echo ""
echo "📋 Next steps:"
echo "   1. RustRover should start automatically"
echo "   2. The WebAssembly GC plugin (v1.5-gc) should now be active"
echo "   3. Open any .wat/.wast file to test GC syntax highlighting"
echo ""
echo "⚠️  Important: If the plugin doesn't load:"
echo "   1. Fully quit RustRover (Cmd+Q)"
echo "   2. Reopen RustRover"
echo "   3. The plugin should now be loaded"
echo ""
echo "To verify installation:"
echo "   Settings → Plugins → Installed → search for 'WebAssembly'"
echo "   Version should show: 1.5-gc"
