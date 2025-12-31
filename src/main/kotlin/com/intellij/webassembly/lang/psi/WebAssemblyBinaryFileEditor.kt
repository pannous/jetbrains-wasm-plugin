package com.intellij.webassembly.lang.psi

import com.intellij.codeHighlighting.BackgroundEditorHighlighter
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider
import com.intellij.openapi.fileEditor.impl.text.TextEditorState
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.testFramework.LightVirtualFile
import com.intellij.webassembly.lang.WebAssemblyLanguage
import java.beans.PropertyChangeListener
import java.io.File
import javax.swing.JComponent

/**
 * Custom editor for .wasm files that shows decompiled WAT and recompiles on save
 */
class WebAssemblyBinaryFileEditor(
    private val project: Project,
    private val wasmFile: VirtualFile
) : FileEditor {

    private val watFile: LightVirtualFile
    val textEditor: TextEditor
    private var lastSavedContent: String

    init {
        // Decompile wasm to wat
        val watContent = decompileWasm(wasmFile)
        lastSavedContent = watContent

        // Create virtual WAT file for editing
        watFile = LightVirtualFile("${wasmFile.nameWithoutExtension}.wat", WebAssemblyLanguage, watContent)
        watFile.isWritable = true

        // Create text editor for the WAT content
        textEditor = TextEditorProvider.getInstance().createEditor(project, watFile) as TextEditor

        // Listen for document changes
        textEditor.editor.document.addDocumentListener(object : com.intellij.openapi.editor.event.DocumentListener {
            override fun documentChanged(event: com.intellij.openapi.editor.event.DocumentEvent) {
                // Mark as modified when content changes
                FileDocumentManager.getInstance().requestWriting(textEditor.editor.document, project)
            }
        })
    }

    override fun getComponent(): JComponent = textEditor.component

    override fun getPreferredFocusedComponent(): JComponent? = textEditor.preferredFocusedComponent

    override fun getName(): String = "WebAssembly Decompiled"

    override fun setState(state: FileEditorState) {
        if (state is TextEditorState) {
            textEditor.setState(state)
        }
    }

    override fun isModified(): Boolean {
        val currentContent = textEditor.editor.document.text
        return currentContent != lastSavedContent
    }

    override fun isValid(): Boolean = wasmFile.isValid && textEditor.isValid

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {
        textEditor.addPropertyChangeListener(listener)
    }

    override fun removePropertyChangeListener(listener: PropertyChangeListener) {
        textEditor.removePropertyChangeListener(listener)
    }

    override fun getFile(): VirtualFile = wasmFile

    override fun dispose() {
        textEditor.dispose()
    }

    override fun <T : Any?> getUserData(key: Key<T>): T? = textEditor.getUserData(key)

    override fun <T : Any?> putUserData(key: Key<T>, value: T?) {
        textEditor.putUserData(key, value)
    }

    override fun getCurrentLocation(): FileEditorLocation? = textEditor.currentLocation

    override fun getBackgroundHighlighter(): BackgroundEditorHighlighter? = textEditor.backgroundHighlighter

    /**
     * Called when the user saves the file - recompile WAT back to WASM
     */
    fun saveWatToWasm(): Boolean {
        val watContent = textEditor.editor.document.text

        return try {
            // Create temp files for compilation
            val tempWat = File.createTempFile("edit-", ".wat")
            val tempWasm = File.createTempFile("compiled-", ".wasm")

            try {
                // Write WAT content to temp file
                tempWat.writeText(watContent)

                // Compile WAT to WASM using available toolchain
                val result = com.intellij.webassembly.lang.WebAssemblyTools.compileWatToWasm(tempWat, tempWasm)

                if (result.success) {
                    // Compilation successful - overwrite original .wasm file
                    ApplicationManager.getApplication().runWriteAction {
                        wasmFile.setBinaryContent(tempWasm.readBytes())
                    }

                    // Update last saved content
                    lastSavedContent = watContent

                    // Force VFS refresh to ensure file system is synced
                    wasmFile.refresh(false, true)

                    // Show success notification
                    com.intellij.notification.Notifications.Bus.notify(
                        com.intellij.notification.Notification(
                            "WebAssembly",
                            "WASM Saved",
                            "Compiled and saved to ${wasmFile.name}",
                            com.intellij.notification.NotificationType.INFORMATION
                        ),
                        project
                    )

                    true
                } else {
                    // Compilation failed - show error (file not saved)
                    com.intellij.openapi.ui.Messages.showErrorDialog(
                        project,
                        result.error ?: "Unknown compilation error",
                        "WebAssembly Compilation Error - File Not Saved"
                    )
                    false
                }
            } finally {
                tempWat.delete()
                tempWasm.delete()
            }
        } catch (e: Exception) {
            com.intellij.openapi.ui.Messages.showErrorDialog(
                project,
                "Error during compilation:\n\n${e.message}",
                "WebAssembly Compilation Error"
            )
            false
        }
    }

    private fun decompileWasm(file: VirtualFile): String {
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
}
