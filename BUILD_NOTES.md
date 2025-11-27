# Build Notes

## Known Issue: Kotlin/Java Compilation Order

The project currently has a Kotlin/Java joint compilation issue where the Kotlin compiler cannot find the generated `_WebAssemblyLexer` class at compile time.

### Root Cause
- Generated Java files are in `src/main/gen` and committed to git
- Kotlin code in `WebAssemblyLexer.kt` references the generated `_WebAssemblyLexer` class
- The Kotlin Gradle plugin's joint compilation runs Kotlin before Java classes are available
- This creates an "Unresolved reference: _WebAssemblyLexer" error

### Workarounds Attempted
1. ✗ Making Kotlin depend on Java compilation - creates circular dependency
2. ✗ Using `mustRunAfter` - doesn't enforce dependency
3. ✗ Configuring source sets - joint compilation overrides it
4. ✗ Two-stage build script - Gradle still resolves Kotlin tasks

### Potential Solutions
1. **Separate Gradle module** for generated sources (proper but requires restructuring)
2. **Disable Kotlin/Java joint compilation** (may break other features)
3. **Use reflection** in WebAssemblyLexer.kt instead of direct instantiation
4. **Pre-compiled JAR** of generated sources as a dependency

### Current Status
- ✅ Grammar files updated with WebAssembly GC support
- ✅ Generated parser/lexer files committed to git (128 files)
- ✅ Gradle configured for Java 17
- ❌ Build currently fails at `compileKotlin` step

### For Development
If you need to modify grammar files:
```bash
./gradlew generateWebAssemblyLexer generateWebAssemblyParser
git add src/main/gen
git commit -m "chore: regenerate parser/lexer"
```

The generated files are the main deliverable - they contain all the WebAssembly GC syntax support.
