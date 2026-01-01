package com.intellij.webassembly.wit

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class WitFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, WitLanguage) {
    override fun getFileType() = WitFileType
    override fun toString() = "WIT File"
}
