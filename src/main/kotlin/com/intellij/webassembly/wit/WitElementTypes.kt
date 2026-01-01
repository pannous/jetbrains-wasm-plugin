package com.intellij.webassembly.wit

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.psi.tree.IElementType
import javax.swing.Icon

class WitTokenType(debugName: String) : IElementType(debugName, WitLanguage) {
    override fun toString(): String = "WitToken." + super.toString()
}

class WitElementType(debugName: String) : IElementType(debugName, WitLanguage)

object WitFileType : LanguageFileType(WitLanguage) {
    override fun getName(): String = "WIT"
    override fun getDescription(): String = "WebAssembly Interface Type"
    override fun getDefaultExtension(): String = "wit"
    override fun getIcon(): Icon = WitIcons.WIT
}
