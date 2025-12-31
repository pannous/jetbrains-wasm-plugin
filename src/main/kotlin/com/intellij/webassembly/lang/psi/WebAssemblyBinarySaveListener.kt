package com.intellij.webassembly.lang.psi

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.LocalFileSystem
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

    // Map from .wat file path to .wasm file path (persistent across saves)
    private val watToWasmMap = mutableMapOf<String, String>()

    // Track .wat files that are currently being initialized (skip notification for first write)
    private val initializingFiles = mutableSetOf<String>()

    init {
        val connection = ApplicationManager.getApplication().messageBus.connect()
        connection.subscribe(VirtualFileManager.VFS_CHANGES, this)
    }

    fun registerMapping(watPath: String, wasmPath: String) {
        println("WebAssembly: Registering mapping: $watPath -> $wasmPath")
        watToWasmMap[watPath] = wasmPath
        // Mark this file as initializing to skip notification on first write
        initializingFiles.add(watPath)
    }

    override fun after(events: List<VFileEvent>) {
        for (event in events) {
            if (event is VFileContentChangeEvent) {
                val watFile = event.file
                println("WebAssembly: File changed: ${watFile.path}")
                if (watFile.extension == "wat") {
                    // Check if this is the initial decompilation (skip notification)
                    if (initializingFiles.remove(watFile.path)) {
                        println("WebAssembly: Skipping notification for initial decompilation")
                        continue
                    }

                    // Check if this .wat file was decompiled from a .wasm file
                    val wasmPath = watToWasmMap[watFile.path]
                    println("WebAssembly: .wat file changed, mapped wasmPath=$wasmPath")
                    if (wasmPath != null) {
                        val wasmFile = LocalFileSystem.getInstance().findFileByPath(wasmPath)
                        if (wasmFile != null) {
                            // This .wat file came from a .wasm - recompile it
                            println("WebAssembly: Recompiling ${watFile.path} -> ${wasmFile.path}")
                            recompileWatToWasm(watFile, wasmFile)
                        } else {
                            println("WebAssembly: ERROR - wasmFile not found at $wasmPath")
                        }
                    }
                }
            }
        }
    }

    private fun recompileWatToWasm(watFile: VirtualFile, wasmFile: VirtualFile) {
        try {
            val watPath = File(watFile.path)
            val wasmPath = File(wasmFile.path)

            println("WebAssembly: Starting compilation...")
            println("WebAssembly: WAT file exists: ${watPath.exists()}, size: ${watPath.length()}")
            println("WebAssembly: WASM file before: ${wasmPath.exists()}, size: ${wasmPath.length()}")

            // Compile WAT to WASM
            val result = com.intellij.webassembly.lang.WebAssemblyTools.compileWatToWasm(watPath, wasmPath)

            println("WebAssembly: Compilation result: success=${result.success}, error=${result.error}")
            println("WebAssembly: WASM file after: ${wasmPath.exists()}, size: ${wasmPath.length()}")

            // Find active project for notifications
            val project = findProject()

            if (result.success) {
                // Refresh the .wasm file in VFS
                ApplicationManager.getApplication().runWriteAction {
                    wasmFile.refresh(false, false)
                }
                println("WebAssembly: VFS refresh completed")

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
