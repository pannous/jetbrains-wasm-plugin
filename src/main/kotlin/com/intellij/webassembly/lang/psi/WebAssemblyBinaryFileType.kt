package com.intellij.webassembly.lang.psi

import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.webassembly.WebassemblyIcons
import javax.swing.Icon

object WebAssemblyBinaryFileType : FileType {
    override fun getName(): String = "WebAssembly Binary"

    override fun getDescription(): String = "WebAssembly Binary File"

    override fun getDefaultExtension(): String = "wasm"

    override fun getIcon(): Icon = WebassemblyIcons.WebAssemblyFiletype

    override fun isBinary(): Boolean = true

    override fun isReadOnly(): Boolean = false
}
