[This](https://github.com/pannous/intellij-webassembly-plugin) is a fork of an official [WebAssembly](https://webassembly.org/) plugin for JetBrains IDEs

## New Features

* **Unified WebAssembly Toolchain** - Automatic fallback between wasm-tools, wabt, and binaryen
* **Binary Decompilation** - Integrated wasm-tools decompiler for viewing .wasm files as .wat
* **Editable .wasm Files** - Binary .wasm files are now editable as .wat / .wast with automatic recompilation on save
* **Syntax Highlighting for .wasm** - Full syntax highlighting applied to decompiled binary files
* **Run/Debug Configuration** - Execute .wat files directly from the IDE with right-click context menu
* **28 Live Templates** - Comprehensive code completion templates for:
  - Module structure (`module`, `import`, `export`)
  - Functions and locals (`func`, `param`, `result`, `local`)
  - Control flow (`if`, `loop`, `block`, `br`, `call`)
  - Memory operations (`load`, `store`, `memory.grow`)
  - Constants and variables (`i32.const`, `i64.const`, `f32.const`, `f64.const`)
  - Type conversions and extensions (`i32.wrap_i64`, `i64.extend_i32_s`, etc.)
  - Reference types and GC support

### Improvements
* Complete heap type support ( struct, array, `ref.null`, `ref.test`, `ref.cast` … )

##### Key features for wat-files:
* Syntax highlighting
* Minor features
  - brace matching
  - commenter
  - code folding
* Code completion
* References and navigation
* Features of [the new release](https://webassembly.github.io/reference-types/core/)
  - bulk instructions
  - reference types


##### Issues/Feature Requests


##### Code contributions
See the [development guide](developer_environment.md) to get up and running.
