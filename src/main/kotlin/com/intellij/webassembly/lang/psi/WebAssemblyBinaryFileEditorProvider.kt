package com.intellij.webassembly.lang.psi

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

class WebAssemblyBinaryFileEditorProvider : FileEditorProvider, DumbAware {
    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.extension == "wasm"
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        // Create a real .wat file on disk next to the .wasm file
        val wasmPath = file.path
        val watPath = wasmPath.removeSuffix(".wasm") + ".wat"
        val watFile = File(watPath)

        // Decompile .wasm to .wat and write to disk
        val watContent = decompileWasmToWat(file)
        ApplicationManager.getApplication().runWriteAction {
            watFile.writeText(watContent)
        }

        // Refresh VFS to detect the new .wat file
        val watVirtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(watPath)
            ?: throw IllegalStateException("Failed to create .wat file at $watPath")

        // Store reference to the original .wasm file
        watVirtualFile.putUserData(WASM_SOURCE_FILE_KEY, file)

        // Open the .wat file in a text editor
        return TextEditorProvider.getInstance().createEditor(project, watVirtualFile)
    }

    override fun getEditorTypeId(): String = "wasm-decompiled-editor"

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR

    private fun decompileWasmToWat(file: VirtualFile): String {
        return try {
            val tempFile = File.createTempFile("wasm-", ".wasm")
            try {
                tempFile.writeBytes(file.contentsToByteArray())
                val result = com.intellij.webassembly.lang.WebAssemblyTools.decompileWasmToWat(tempFile)
                result.watContent
            } finally {
                tempFile.delete()
            }
        } catch (e: Exception) {
            """;; Error: Failed to decompile WebAssembly binary
               |;; ${e.message}
            """.trimMargin()
        }
    }

    companion object {
        val WASM_SOURCE_FILE_KEY = com.intellij.openapi.util.Key.create<VirtualFile>("WASM_SOURCE_FILE")
    }
}

