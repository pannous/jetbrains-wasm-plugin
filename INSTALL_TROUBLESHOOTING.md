# Plugin Installation Troubleshooting

## Issue: Plugin doesn't load after running install script

The install script successfully installs the plugin, but sometimes JetBrains IDEs need a full restart to load new plugins.

### Solution 1: Full IDE Restart (Recommended)

1. **Fully quit RustRover** (or other IDE):
   - Press `Cmd+Q` (Mac) / `Ctrl+Q` (Linux)
   - Or: Menu → File → Exit
   - **Important**: Don't just close the window!

2. **Reopen RustRover**:
   - The plugin should now be loaded

3. **Verify installation**:
   - Go to: Settings → Plugins → Installed
   - Search for "WebAssembly"
   - Version should show: `1.5-gc`

### Solution 2: Manual Installation

If the script still doesn't work:

1. **Build the plugin**:
   ```bash
   ./build.sh
   ```

2. **Install manually**:
   - Open RustRover
   - Go to: Settings → Plugins
   - Click ⚙️ (gear icon) → Install Plugin from Disk
   - Navigate to: `build/distributions/intellij-webassembly-plugin-1.5-gc.zip`
   - Select the file and click OK
   - Restart IDE when prompted

### Verification

After installation, test the plugin:

1. **Create a test file** `test.wat`:
   ```wat
   (module
     ;; GC features
     (type $string (array (mut i8)))
     (type $numbers (array i32))

     (type $point (struct
       (field $x i32)
       (field $y (mut i32))
     ))

     (func $test
       (local $arr (ref null $string))
       (local $pt (ref null $point))

       (array.new $string (i32.const 0) (i32.const 10))
       (local.set $arr)

       (struct.new $point (i32.const 5) (i32.const 10))
       (local.set $pt)
     )
   )
   ```

2. **Check syntax highlighting**:
   - Keywords should be highlighted (blue)
   - Types should be highlighted (purple)
   - Comments should be gray
   - No parse errors

3. **Check features**:
   - GC keywords: `array`, `struct`, `ref`, `mut`
   - GC instructions: `array.new`, `struct.new`
   - Reference types: `(ref null $type)`

## Why Manual Installation Works

The install script copies the plugin files, but JetBrains IDEs cache plugin information:
- Manual installation triggers IDE's plugin loading mechanism
- Full restart (Cmd+Q) clears the cache
- Window close/reopen doesn't reload plugins

## Script Installation Locations

The script installs to:
```
~/Library/Application Support/JetBrains/RustRover2025.2/plugins/intellij-webassembly-plugin/
```

You can verify the file exists:
```bash
ls -la ~/Library/Application\ Support/JetBrains/RustRover2025.2/plugins/intellij-webassembly-plugin/lib/
```

Should show:
- `instrumented-intellij-webassembly-plugin-1.5-gc.jar` (latest build)
- `kotlin-stdlib-1.9.20.jar`
- Other dependencies
