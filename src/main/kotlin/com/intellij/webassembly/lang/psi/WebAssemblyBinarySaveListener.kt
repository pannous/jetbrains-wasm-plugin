package com.intellij.webassembly.lang.psi

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileContentChangeEvent
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import java.io.File

/**
 * Service that watches for .wat file saves and automatically recompiles to .wasm
 */
@Service
class WebAssemblyBinarySaveListener : BulkFileListener {

    init {
        val connection = ApplicationManager.getApplication().messageBus.connect()
        connection.subscribe(VirtualFileManager.VFS_CHANGES, this)
    }

    override fun after(events: List<VFileEvent>) {
        for (event in events) {
            if (event is VFileContentChangeEvent) {
                val watFile = event.file
                if (watFile.extension == "wat") {
                    // Check if this .wat file was decompiled from a .wasm file
                    val wasmFile = watFile.getUserData(WebAssemblyBinaryFileEditorProvider.WASM_SOURCE_FILE_KEY)
                    if (wasmFile != null) {
                        // This .wat file came from a .wasm - recompile it
                        recompileWatToWasm(watFile, wasmFile)
                    }
                }
            }
        }
    }

    private fun recompileWatToWasm(watFile: VirtualFile, wasmFile: VirtualFile) {
        try {
            val watPath = File(watFile.path)
            val wasmPath = File(wasmFile.path)

            // Compile WAT to WASM
            val result = com.intellij.webassembly.lang.WebAssemblyTools.compileWatToWasm(watPath, wasmPath)

            // Find active project for notifications
            val project = findProject()

            if (result.success) {
                // Refresh the .wasm file in VFS
                ApplicationManager.getApplication().runWriteAction {
                    wasmFile.refresh(false, false)
                }

                // Show success notification
                Notifications.Bus.notify(
                    Notification(
                        "WebAssembly",
                        "WASM Compiled",
                        "Saved ${watFile.name} → ${wasmFile.name}",
                        NotificationType.INFORMATION
                    ),
                    project
                )
            } else {
                // Show error
                Messages.showErrorDialog(
                    project,
                    result.error ?: "Unknown compilation error",
                    "WebAssembly Compilation Error"
                )
            }
        } catch (e: Exception) {
            Messages.showErrorDialog(
                findProject(),
                "Error recompiling: ${e.message}",
                "WebAssembly Error"
            )
        }
    }

    private fun findProject(): Project? {
        val projects = ProjectManager.getInstance().openProjects
        return if (projects.isNotEmpty()) projects[0] else null
    }

    companion object {
        @JvmStatic
        fun getInstance(): WebAssemblyBinarySaveListener {
            return ApplicationManager.getApplication().getService(WebAssemblyBinarySaveListener::class.java)
        }
    }
}
