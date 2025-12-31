package com.intellij.webassembly.lang.psi

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

class WebAssemblyBinaryFileEditorProvider : FileEditorProvider, DumbAware {
    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.extension == "wasm"
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        // Check if a .wat file already exists next to the .wasm (user's source file)
        val siblingWatPath = file.path.removeSuffix(".wasm") + ".wat"
        val siblingWatFile = LocalFileSystem.getInstance().findFileByPath(siblingWatPath)

        val watVirtualFile = if (siblingWatFile != null && siblingWatFile.exists()) {
            // Ask user what to do with existing .wat file
            val choice = Messages.showDialog(
                project,
                "A .wat file already exists next to ${file.name}.\nWhat would you like to do?",
                "Existing WAT File Found",
                arrayOf("Use Existing", "Override with Decompiled", "Cancel"),
                0,
                Messages.getQuestionIcon()
            )

            when (choice) {
                0 -> { // Use Existing
                    siblingWatFile.putUserData(WASM_SOURCE_FILE_KEY, file)
                    return TextEditorProvider.getInstance().createEditor(project, siblingWatFile)
                }
                1 -> { // Override with Decompiled
                    val watContent = decompileWasmToWat(file)
                    ApplicationManager.getApplication().runWriteAction {
                        siblingWatFile.setBinaryContent(watContent.toByteArray())
                    }
                    siblingWatFile.putUserData(WASM_SOURCE_FILE_KEY, file)
                    siblingWatFile
                }
                else -> { // Cancel
                    throw ProcessCanceledException()
                }
            }
        } else {
            // No existing .wat file - create in cache directory
            val cacheDir = File(System.getProperty("java.io.tmpdir"), "intellij-webassembly-decompiled")
            cacheDir.mkdirs()

            val wasmPathHash = file.path.hashCode().toString().replace("-", "")
            val watPath = File(cacheDir, "${file.nameWithoutExtension}_$wasmPathHash.wat").absolutePath
            val watFile = File(watPath)

            // Decompile .wasm to .wat and write to disk
            val watContent = decompileWasmToWat(file)
            ApplicationManager.getApplication().runWriteAction {
                watFile.writeText(watContent)
            }

            // Refresh VFS to detect the new .wat file
            val createdWatFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(watPath)
                ?: throw IllegalStateException("Failed to create .wat file at $watPath")

            // Store reference to the original .wasm file
            createdWatFile.putUserData(WASM_SOURCE_FILE_KEY, file)
            createdWatFile
        }

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

