package com.intellij.webassembly.lang.psi

import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.testFramework.LightVirtualFile
import com.intellij.webassembly.lang.WebAssemblyLanguage
import java.io.File

class WebAssemblyBinaryFileEditorProvider : FileEditorProvider, DumbAware {
    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.extension == "wasm"
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        val decompiled = decompileWasm(file)

        // Create a light virtual file with the decompiled content
        val lightFile = LightVirtualFile("${file.nameWithoutExtension}.wat", WebAssemblyLanguage, decompiled)
        lightFile.isWritable = false

        // Create a text editor for the decompiled content
        return TextEditorProvider.getInstance().createEditor(project, lightFile)
    }

    override fun getEditorTypeId(): String = "wasm-decompiled-editor"

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR

    private fun decompileWasm(file: VirtualFile): String {
        return try {
            val tempFile = File.createTempFile("wasm-", ".wasm")
            try {
                tempFile.writeBytes(file.contentsToByteArray())

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
                    """;; Error decompiling WebAssembly binary:
                       |;; $output
                       |;;
                       |;; Make sure wasm-tools is installed:
                       |;;   brew install wasm-tools
                       |;;   or download from: https://github.com/bytecodealliance/wasm-tools
                    """.trimMargin()
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
}
