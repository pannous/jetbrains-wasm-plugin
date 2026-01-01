package com.intellij.webassembly.wit

import com.intellij.psi.tree.IElementType

object WitTypes {
    // Keywords
    @JvmField val RESERVED_KEYWORD = WitTokenType("RESERVED_KEYWORD")
    @JvmField val TYPE_DECLARE_KEYWORD = WitTokenType("TYPE_DECLARE_KEYWORD")
    @JvmField val DEFAULT_KEYWORD = WitTokenType("DEFAULT_KEYWORD")
    @JvmField val INTERFACE_KEYWORD = WitTokenType("INTERFACE_KEYWORD")
    @JvmField val PACKAGE_KEYWORD = WitTokenType("PACKAGE_KEYWORD")
    @JvmField val WORLD_KEYWORD = WitTokenType("WORLD_KEYWORD")
    @JvmField val FUNC_KEYWORD = WitTokenType("FUNC_KEYWORD")
    @JvmField val REFER_KEYWORD = WitTokenType("REFER_KEYWORD")
    @JvmField val PATH_PREFIX_KEYWORD = WitTokenType("PATH_PREFIX_KEYWORD")

    // Comments
    @JvmField val COMMENT = WitTokenType("COMMENT")
    @JvmField val DOC_COMMENT = WitTokenType("DOC_COMMENT")
    @JvmField val COMMENT_BLOCK = WitTokenType("COMMENT_BLOCK")
    @JvmField val BLOCK_COMMENT_START = WitTokenType("BLOCK_COMMENT_START")
    @JvmField val BLOCK_COMMENT_END = WitTokenType("BLOCK_COMMENT_END")

    // Identifiers and names
    @JvmField val WIT_TYPE = WitTokenType("WIT_TYPE")
    @JvmField val FUNC_NAME = WitTokenType("FUNC_NAME")
    @JvmField val FUNC_PARAM_NAME = WitTokenType("FUNC_PARAM_NAME")
    @JvmField val INTERFACE_NAME = WitTokenType("INTERFACE_NAME")
    @JvmField val PACKAGE_NAMESPACE = WitTokenType("PACKAGE_NAMESPACE")
    @JvmField val UNION_ITEM_NAME = WitTokenType("UNION_ITEM_NAME")
    @JvmField val ENUM_ITEM_NAME = WitTokenType("ENUM_ITEM_NAME")
    @JvmField val WORLD_NAME = WitTokenType("WORLD_NAME")
    @JvmField val RECORD_ITEM_NAME = WitTokenType("RECORD_ITEM_NAME")
    @JvmField val IDENTIFIER = WitTokenType("IDENTIFIER")

    // Literals
    @JvmField val INTEGER_LITERAL = WitTokenType("INTEGER_LITERAL")
    @JvmField val DOUBLE_LITERAL = WitTokenType("DOUBLE_LITERAL")
    @JvmField val STRING_LITERAL = WitTokenType("STRING_LITERAL")
    @JvmField val CHAR_LITERAL = WitTokenType("CHAR_LITERAL")
    @JvmField val SEMANTIC_VERSION = WitTokenType("SEMANTIC_VERSION")

    // Types
    @JvmField val BUILTIN_TYPE = WitTokenType("BUILTIN_TYPE")

    // Operators and delimiters
    @JvmField val COMMA = WitTokenType("COMMA")
    @JvmField val LBRACE = WitTokenType("LBRACE")
    @JvmField val RBRACE = WitTokenType("RBRACE")
    @JvmField val LBRACK = WitTokenType("LBRACK")
    @JvmField val RBRACK = WitTokenType("RBRACK")
    @JvmField val LPAREN = WitTokenType("LPAREN")
    @JvmField val RPAREN = WitTokenType("RPAREN")
    @JvmField val COLON = WitTokenType("COLON")
    @JvmField val SEMICOLON = WitTokenType("SEMICOLON")
    @JvmField val DOT = WitTokenType("DOT")
    @JvmField val EQ = WitTokenType("EQ")
    @JvmField val PLUS = WitTokenType("PLUS")
    @JvmField val MINUS = WitTokenType("MINUS")
    @JvmField val MUL = WitTokenType("MUL")
    @JvmField val DIV = WitTokenType("DIV")
    @JvmField val REM = WitTokenType("REM")
    @JvmField val OR = WitTokenType("OR")
    @JvmField val LT = WitTokenType("LT")
    @JvmField val GT = WitTokenType("GT")
    @JvmField val ARROW = WitTokenType("ARROW")
    @JvmField val AT = WitTokenType("AT")

    // Gate annotations
    @JvmField val GATE_UNSTABLE_WORD = WitTokenType("GATE_UNSTABLE_WORD")
    @JvmField val GATE_SINCE_WORD = WitTokenType("GATE_SINCE_WORD")
    @JvmField val GATE_DEPRECATED_WORD = WitTokenType("GATE_DEPRECATED_WORD")
}
