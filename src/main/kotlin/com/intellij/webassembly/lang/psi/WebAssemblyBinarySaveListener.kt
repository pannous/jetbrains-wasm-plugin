package com.intellij.webassembly.lang.psi

import com.intellij.AppTopics
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager

/**
 * Service that intercepts document saves and triggers WAT → WASM recompilation
 */
@Service
class WebAssemblyBinarySaveListener : FileDocumentManagerListener {

    init {
        // Subscribe to file document sync events
        val connection = ApplicationManager.getApplication().messageBus.connect()
        connection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, this)
    }

    override fun beforeDocumentSaving(document: Document) {
        val file = FileDocumentManager.getInstance().getFile(document) ?: return

        // Only handle LightVirtualFiles with .wat extension (our decompiled views)
        if (file !is com.intellij.testFramework.LightVirtualFile) return
        if (!file.name.endsWith(".wat")) return

        // Check if this document has a WebAssemblyBinaryFileEditor attached
        val editor = document.getUserData(WASM_EDITOR_KEY)
        if (editor != null) {
            // Trigger recompilation to .wasm file
            ApplicationManager.getApplication().invokeLater {
                editor.saveWatToWasm()
            }
            return
        }
    }

    companion object {
        private val WASM_EDITOR_KEY = com.intellij.openapi.util.Key.create<WebAssemblyBinaryFileEditor>("WASM_BINARY_EDITOR")

        @JvmStatic
        fun getInstance(): WebAssemblyBinarySaveListener {
            return ApplicationManager.getApplication().getService(WebAssemblyBinarySaveListener::class.java)
        }
    }
}
