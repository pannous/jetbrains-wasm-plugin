package com.intellij.webassembly.wit

import com.intellij.lexer.FlexAdapter
import com.intellij.webassembly.wit.lexer.WitLexer

class WitLexerAdapter : FlexAdapter(WitLexer(null))
