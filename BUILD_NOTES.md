# Build Notes

## ✅ Kotlin/Java Compilation Issue RESOLVED

The build previously failed due to a Kotlin/Java joint compilation issue. This has been resolved by converting key Kotlin files to Java.

### Solution Implemented
- Converted `WebAssemblyLexer` and `WebAssemblyParserDefinition` from Kotlin to Java
- Created Java base classes in `org.jetbrains.webstorm.lang.psi` package for generated code
- Added missing imports to generated lexer file
- Added SUBKEY and FINALKEY tokens for WebAssembly GC subtyping support

### Current Status
- ✅ Grammar files updated with WebAssembly GC support
- ✅ Generated parser/lexer files committed to git
- ✅ Gradle configured for Java 17
- ✅ Build completes successfully
- ✅ Plugin installs correctly to JetBrains IDEs

### Building the Plugin
```bash
./build.sh          # Builds the plugin
./install-plugin.sh # Builds and installs to all detected JetBrains IDEs
```

### For Development
If you need to modify grammar files:
```bash
# Modify src/main/grammars/WebAssemblyParser.bnf or WebAssemblyLexer.flex
# Then regenerate (note: requires grammarkit task configuration)
./gradlew generateParser generateLexer
git add src/main/gen
git commit -m "chore: regenerate parser/lexer"
```

The generated files in `src/main/gen` contain all the WebAssembly GC syntax support including:
- Reference types (ref, anyref, funcref, externref, etc.)
- Struct types and operations (struct.new, struct.get, struct.set)
- Array types and operations (array.new, array.get, array.set)
- GC-specific instructions (ref.null, ref.is_null, ref.as_non_null, etc.)
