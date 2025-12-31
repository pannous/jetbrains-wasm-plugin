package com.intellij.webassembly.lang

import java.io.File

/**
 * Unified WebAssembly toolchain manager with automatic fallback support.
 * Supports three major toolchains:
 * 1. wasm-tools (Bytecode Alliance) - modern, recommended
 * 2. wabt (WebAssembly Binary Toolkit) - traditional, widely used
 * 3. binaryen - alternative toolkit
 */
object WebAssemblyTools {

    enum class Toolchain(
        val watToWasm: String,
        val wasmToWat: String,
        val displayName: String,
        val installHint: String
    ) {
        WASM_TOOLS(
            "wasm-tools",
            "wasm-tools",
            "wasm-tools (Bytecode Alliance)",
            "brew install wasm-tools"
        ),
        WABT(
            "wat2wasm",
            "wasm2wat",
            "WABT (WebAssembly Binary Toolkit)",
            "brew install wabt"
        ),
        BINARYEN(
            "wasm-as",
            "wasm-dis",
            "Binaryen",
            "brew install binaryen"
        )
    }

    private var cachedToolchain: Toolchain? = null

    /**
     * Detect which WebAssembly toolchain is available, with fallback priority:
     * 1. wasm-tools (modern, preferred)
     * 2. wabt (traditional)
     * 3. binaryen (alternative)
     */
    fun detectToolchain(): Toolchain? {
        if (cachedToolchain != null) return cachedToolchain

        for (toolchain in Toolchain.values()) {
            if (isToolAvailable(toolchain.watToWasm)) {
                cachedToolchain = toolchain
                return toolchain
            }
        }

        return null
    }

    /**
     * Compile WAT (WebAssembly Text) to WASM (binary)
     */
    fun compileWatToWasm(watFile: File, wasmFile: File): CompileResult {
        val toolchain = detectToolchain()
            ?: return CompileResult.failure("No WebAssembly toolchain found. Install one of:\n" +
                    "  - ${Toolchain.WASM_TOOLS.installHint}\n" +
                    "  - ${Toolchain.WABT.installHint}\n" +
                    "  - ${Toolchain.BINARYEN.installHint}")

        return try {
            val args = when (toolchain) {
                Toolchain.WASM_TOOLS -> listOf("parse", watFile.absolutePath, "-o", wasmFile.absolutePath)
                Toolchain.WABT -> listOf(watFile.absolutePath, "-o", wasmFile.absolutePath)
                Toolchain.BINARYEN -> listOf(watFile.absolutePath, "-o", wasmFile.absolutePath)
            }

            val process = ProcessBuilder(toolchain.watToWasm, *args.toTypedArray())
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                CompileResult.success()
            } else {
                CompileResult.failure("${toolchain.displayName} compilation failed:\n$output")
            }
        } catch (e: Exception) {
            CompileResult.failure("${toolchain.displayName} error: ${e.message}")
        }
    }

    /**
     * Decompile WASM (binary) to WAT (WebAssembly Text)
     */
    fun decompileWasmToWat(wasmFile: File): DecompileResult {
        val toolchain = detectToolchain()
            ?: return DecompileResult.failure(";; No WebAssembly toolchain found. Install one of:\n" +
                    ";;   ${Toolchain.WASM_TOOLS.installHint}\n" +
                    ";;   ${Toolchain.WABT.installHint}\n" +
                    ";;   ${Toolchain.BINARYEN.installHint}")

        return try {
            val args = when (toolchain) {
                Toolchain.WASM_TOOLS -> listOf("print", wasmFile.absolutePath)
                Toolchain.WABT -> listOf(wasmFile.absolutePath)
                Toolchain.BINARYEN -> listOf(wasmFile.absolutePath)
            }

            val process = ProcessBuilder(toolchain.wasmToWat, *args.toTypedArray())
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                DecompileResult.success(output)
            } else {
                DecompileResult.failure(
                    ";; ${toolchain.displayName} decompilation failed:\n;; $output"
                )
            }
        } catch (e: Exception) {
            DecompileResult.failure(
                ";; ${toolchain.displayName} error: ${e.message}"
            )
        }
    }

    private fun isToolAvailable(command: String): Boolean {
        return try {
            val process = ProcessBuilder(command, "--version")
                .redirectErrorStream(true)
                .start()
            process.waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Clear cached toolchain (useful for testing or when tools are installed)
     */
    fun clearCache() {
        cachedToolchain = null
    }

    data class CompileResult(
        val success: Boolean,
        val error: String?
    ) {
        companion object {
            fun success() = CompileResult(true, null)
            fun failure(error: String) = CompileResult(false, error)
        }
    }

    data class DecompileResult(
        val success: Boolean,
        val watContent: String
    ) {
        companion object {
            fun success(content: String) = DecompileResult(true, content)
            fun failure(error: String) = DecompileResult(false, error)
        }
    }
}
