package com.intellij.webassembly.lang.psi

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.FileViewProvider
import com.intellij.psi.FileViewProviderFactory
import com.intellij.psi.PsiManager
import com.intellij.psi.SingleRootFileViewProvider
import com.intellij.webassembly.lang.WebAssemblyLanguage
import java.io.File

class WebAssemblyBinaryFileViewProviderFactory : FileViewProviderFactory {
    override fun createFileViewProvider(
        file: VirtualFile,
        language: Language?,
        manager: PsiManager,
        eventSystemEnabled: Boolean
    ): FileViewProvider {
        return WebAssemblyBinaryFileViewProvider(manager, file, eventSystemEnabled)
    }
}

class WebAssemblyBinaryFileViewProvider(
    manager: PsiManager,
    virtualFile: VirtualFile,
    eventSystemEnabled: Boolean
) : SingleRootFileViewProvider(manager, virtualFile, eventSystemEnabled, WebAssemblyLanguage) {

    override fun getContents(): CharSequence {
        return try {
            val tempFile = File.createTempFile("wasm-", ".wasm")
            try {
                tempFile.writeBytes(virtualFile.contentsToByteArray())

                val process = ProcessBuilder(
                    "wasm-tools",
                    "print",
                    "--name-unnamed",
                    tempFile.absolutePath
                ).redirectErrorStream(true).start()

                val output = process.inputStream.bufferedReader().readText()
                val exitCode = process.waitFor()

                if (exitCode == 0) {
                    output
                } else {
                    ";; Error decompiling WebAssembly binary:\n;; $output\n;;\n" +
                    ";; Make sure wasm-tools is installed:\n" +
                    ";;   brew install wasm-tools\n" +
                    ";;   or download from: https://github.com/bytecodealliance/wasm-tools"
                }
            } finally {
                tempFile.delete()
            }
        } catch (e: Exception) {
            """;; Error: Failed to decompile WebAssembly binary
               |;; ${e.message}
               |;;
               |;; Please install wasm-tools to view WebAssembly binary files:
               |;;   brew install wasm-tools
               |;;   or download from: https://github.com/bytecodealliance/wasm-tools
            """.trimMargin()
        }
    }

    override fun createCopy(copy: VirtualFile): SingleRootFileViewProvider {
        return WebAssemblyBinaryFileViewProvider(manager, copy, false)
    }
}
