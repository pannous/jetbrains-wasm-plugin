Add modern web assembly features to Jetbrains IDEs

https://github.com/pannous/intellij-webassembly-plugin

partly forked from 

https://github.com/JetBrains/intellij-plugins/tree/master/web-assembly-plugin

# TODOs

# DONE

## WIT Syntax Highlighting
✅ Added WIT (WebAssembly Interface Type) syntax highlighting support
- Integrated WIT lexer from wit-jetbrains-plugin
- Created WIT language definition and file type registration
- Syntax highlighting for keywords, types, comments, and literals
- Plugin now recognizes .wit files and provides full syntax highlighting

## Completion templates for
- (module $name )
- (import "env" "toNode" (func $toNode (type 1)))
-   (func $main (result i64)  (local $result i64) )
-     (export "main" (func $main))
-     (i32.const %CURSOR%)
-     ... all other common wat expressions!
✅ Implemented 28 live templates for WebAssembly including module, import, func, export, constants, variables, control flow, and memory operations

  - "Go to Definition" might not work
  - Symbol resolution needs investigation

# IDEAs

Greatly extent the functionality of the plug-in:

Run / Debug option for wasm wast wat wit files

Implement language server to a step through the execution

Allow setting of brake points etc

Maybe merge with wit types https://github.com/linux-china/wit-jetbrains-plugin /opt/wasm/wit-jetbrains-plugin
- Do not report success before trying ./install-plugin.sh
- You may get some inspiration from /opt/other/goo-jetbrains-plugin Things were great over there
- always finish work with ./install-plugin.sh which also recompiles everything