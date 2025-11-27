Add modern web assembly features to Jetbrains IDEs

https://github.com/pannous/intellij-webassembly-plugin

partly forked from 

https://github.com/JetBrains/intellij-plugins/tree/master/web-assembly-plugin

# TODOs

Completion templates for 
- (module $name ) 
- (import "env" "toNode" (func $toNode (type 1)))
-   (func $main (result i64)  (local $result i64) )
-     (export "main" (func $main))
-     ...

  - "Go to Definition" might not work
  - Symbol resolution needs investigation

# IDEAs

Greatly extent the functionality of the plug-in:

Run / Debug option for wasm wast wat wit files

Implement language server to a step through the execution

Allow setting of brake points etc

Maybe merge with wit types https://github.com/linux-china/wit-jetbrains-plugin /opt/wasm/wit-jetbrains-plugin
- Do not report success before trying ./install-plugin.sh