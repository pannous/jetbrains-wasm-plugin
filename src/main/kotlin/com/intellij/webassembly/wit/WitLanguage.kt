package com.intellij.webassembly.wit

import com.intellij.lang.Language

object WitLanguage : Language("WIT") {
    private fun readResolve(): Any = WitLanguage
    override fun isCaseSensitive() = true
}
