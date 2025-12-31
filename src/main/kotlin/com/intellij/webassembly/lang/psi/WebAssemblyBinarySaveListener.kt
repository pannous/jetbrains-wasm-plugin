package com.intellij.webassembly.lang.psi

import com.intellij.AppTopics
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vfs.VirtualFile

/**
 * Service that listens for document saves and triggers WAT → WASM recompilation
 */
@Service
class WebAssemblyBinarySaveListener {

    init {
        // Listen for document saves
        val connection = ApplicationManager.getApplication().messageBus.connect()
        connection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, object : FileDocumentManagerListener {
            override fun beforeDocumentSaving(document: Document) {
                handleDocumentSave(document)
            }
        })
    }

    private fun handleDocumentSave(document: Document) {
        val file = FileDocumentManager.getInstance().getFile(document) ?: return

        // Only handle LightVirtualFiles with .wat extension (our decompiled views)
        if (file !is com.intellij.testFramework.LightVirtualFile) return
        if (!file.name.endsWith(".wat")) return

        // Find the corresponding WebAssemblyBinaryFileEditor
        val projects = ProjectManager.getInstance().openProjects
        for (project in projects) {
            val editorManager = FileEditorManager.getInstance(project)
            val editors = editorManager.allEditors

            for (editor in editors) {
                if (editor is WebAssemblyBinaryFileEditor) {
                    val editorDoc = editor.textEditor.editor.document
                    if (editorDoc == document) {
                        // Found the right editor - trigger recompilation
                        editor.saveWatToWasm()
                        return
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(): WebAssemblyBinarySaveListener {
            return ApplicationManager.getApplication().getService(WebAssemblyBinarySaveListener::class.java)
        }
    }
}
