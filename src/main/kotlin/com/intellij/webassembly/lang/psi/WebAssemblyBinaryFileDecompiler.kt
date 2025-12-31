package com.intellij.webassembly.lang.psi

import com.intellij.openapi.fileTypes.BinaryFileDecompiler
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

class WebAssemblyBinaryFileDecompiler : BinaryFileDecompiler {
    override fun decompile(file: VirtualFile): CharSequence {
        return try {
            val tempFile = File.createTempFile("wasm-", ".wasm")
            try {
                tempFile.writeBytes(file.contentsToByteArray())

                val process = ProcessBuilder(
                    "wasm-tools",
                    "print",
                    "--name-unnamed",  // Generate names for unnamed items
                    tempFile.absolutePath
                ).redirectErrorStream(true)
                    .start()

                val output = process.inputStream.bufferedReader().readText()
                val exitCode = process.waitFor()

                if (exitCode == 0) {
                    output
                } else {
                    "Error decompiling WebAssembly binary:\n$output\n\n" +
                    "Make sure wasm-tools is installed:\n" +
                    "  brew install wasm-tools\n" +
                    "  or download from: https://github.com/bytecodealliance/wasm-tools"
                }
            } finally {
                tempFile.delete()
            }
        } catch (e: Exception) {
            """
            |; Error: Failed to decompile WebAssembly binary
            |; ${e.message}
            |;
            |; Please install wasm-tools to view WebAssembly binary files:
            |;   brew install wasm-tools
            |;   or download from: https://github.com/bytecodealliance/wasm-tools
            """.trimMargin()
        }
    }
}
