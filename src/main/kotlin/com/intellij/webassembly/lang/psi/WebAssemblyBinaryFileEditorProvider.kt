package com.intellij.webassembly.lang.psi

import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class WebAssemblyBinaryFileEditorProvider : FileEditorProvider, DumbAware {
    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.extension == "wasm"
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        return WebAssemblyBinaryFileEditor(project, file)
    }

    override fun getEditorTypeId(): String = "wasm-decompiled-editor"

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}
