package com.intellij.webassembly.lang.lexer;

import com.intellij.lexer.FlexAdapter;

public class WebAssemblyLexer extends FlexAdapter {
    public WebAssemblyLexer() {
        super(new _WebAssemblyLexer(null));
    }
}
