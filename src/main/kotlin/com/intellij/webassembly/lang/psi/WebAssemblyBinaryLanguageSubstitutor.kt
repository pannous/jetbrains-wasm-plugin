package com.intellij.webassembly.lang.psi

import com.intellij.lang.Language
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.LanguageSubstitutor
import com.intellij.webassembly.lang.WebAssemblyLanguage

/**
 * Treats decompiled .wasm binary files as WebAssembly language
 * to enable syntax highlighting of the WAT output from wasm-tools
 */
class WebAssemblyBinaryLanguageSubstitutor : LanguageSubstitutor() {
    override fun getLanguage(file: VirtualFile, project: Project): Language? {
        if (file.extension == "wasm") {
            return WebAssemblyLanguage
        }
        return null
    }
}
