# WebAssembly Run Configuration

This plugin now supports running `.wat` (WebAssembly Text) files directly from your JetBrains IDE.

## Features

- Compiles `.wat` files to `.wasm` using `wat2wasm`
- Executes the compiled WASM module using Node.js
- Automatically calls the `main()` function if exported
- Displays output in the IDE's Run tool window

## Prerequisites

Before using the run configuration, you need to install:

1. **WABT (WebAssembly Binary Toolkit)** - for `wat2wasm` compiler
   ```bash
   # macOS
   brew install wabt

   # Linux (Ubuntu/Debian)
   sudo apt-get install wabt

   # Windows
   # Download from https://github.com/WebAssembly/wabt/releases
   ```

2. **Node.js** - for executing WASM modules
   ```bash
   # macOS
   brew install node

   # Linux (Ubuntu/Debian)
   sudo apt-get install nodejs

   # Windows
   # Download from https://nodejs.org/
   ```

## How to Use

### Method 1: Quick Run from Context Menu (Recommended)

1. Right-click on any `.wat` or `.wast` file in the project tree or editor
2. Click **"More Run/Debug"** in the context menu
3. Select **"Run 'filename.wat'"**

The file will be automatically compiled and executed!

**Alternative shortcuts:**
- With the file open in editor: Press **Ctrl+Shift+F10** (Windows/Linux) or **Cmd+Shift+R** (Mac)
- Click the green run icon in the editor gutter (if available)

### Method 2: Create a Run Configuration Manually

1. Open your `.wat` file in the IDE
2. Go to **Run → Edit Configurations...**
3. Click the **+** button and select **WebAssembly**
4. Give your configuration a name (e.g., "Run test.wat")
5. Browse and select your `.wat` file
6. Click **OK**
7. Click the **Run** button (green play icon) or press Shift+F10

## Example

See `examples/test-run.wat` for a simple example:

```wat
(module
  ;; Simple function that returns 42
  (func $main (result i64)
    i64.const 42
  )

  ;; Export the main function
  (export "main" (func $main))
)
```

When you run this file, you should see:
```
Calling main()...
Result: 42n
main() executed successfully
```

## Requirements for Your WAT Files

To use the run configuration, your `.wat` file must:

1. Be valid WebAssembly text format
2. Export a function named `main` - this is the entry point that will be called
3. The `main` function can have any signature (no params, return values, etc.)

## Troubleshooting

### "wat2wasm not found"
- Make sure WABT is installed: `which wat2wasm` (macOS/Linux) or `where wat2wasm` (Windows)
- Add WABT to your PATH if needed

### "node not found"
- Make sure Node.js is installed: `which node` (macOS/Linux) or `where node` (Windows)
- Add Node.js to your PATH if needed

### "No main() function found"
- Ensure your WAT file exports a function named `main`
- Check the console output for the list of available exports

## Technical Details

The run configuration performs these steps:

1. **Compilation**: Runs `wat2wasm <file>.wat -o <file>.wasm`
2. **Execution**: Creates a temporary Node.js script that:
   - Loads the compiled `.wasm` file
   - Instantiates the WebAssembly module
   - Calls the exported `main()` function
   - Displays the result

## What to Expect When Running

When you run a `.wat` file, the IDE will:

1. Show the Run tool window at the bottom
2. Display compilation output from `wat2wasm`
3. Show execution results from Node.js
4. For the example file, you should see:
   ```
   Calling main()...
   Result: 42n
   main() executed successfully
   ```

## Future Enhancements

Planned improvements:
- Support for custom import objects (WASI imports, custom env functions)
- Full debug configuration support with breakpoints
- Custom entry point function names (not just `main`)
- Input parameter support for the main function
- Better error messages and diagnostics
