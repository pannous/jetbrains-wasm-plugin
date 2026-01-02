package com.intellij.webassembly.wit

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class WitSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = WitLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        val textAttributesKey = when (tokenType) {
            WitTypes.RESERVED_KEYWORD,
            WitTypes.TYPE_DECLARE_KEYWORD,
            WitTypes.DEFAULT_KEYWORD,
            WitTypes.INTERFACE_KEYWORD,
            WitTypes.PACKAGE_KEYWORD,
            WitTypes.WORLD_KEYWORD,
            WitTypes.FUNC_KEYWORD,
            WitTypes.REFER_KEYWORD,
            WitTypes.PATH_PREFIX_KEYWORD -> DefaultLanguageHighlighterColors.KEYWORD

            WitTypes.COMMENT -> DefaultLanguageHighlighterColors.LINE_COMMENT
            WitTypes.DOC_COMMENT -> DefaultLanguageHighlighterColors.DOC_COMMENT
            WitTypes.COMMENT_BLOCK,
            WitTypes.BLOCK_COMMENT_START,
            WitTypes.BLOCK_COMMENT_END -> DefaultLanguageHighlighterColors.BLOCK_COMMENT

            WitTypes.WIT_TYPE,
            WitTypes.BUILTIN_TYPE -> DefaultLanguageHighlighterColors.CLASS_NAME

            WitTypes.FUNC_NAME -> DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
            WitTypes.FUNC_PARAM_NAME -> DefaultLanguageHighlighterColors.PARAMETER
            WitTypes.INTERFACE_NAME -> DefaultLanguageHighlighterColors.INTERFACE_NAME
            WitTypes.PACKAGE_NAMESPACE -> DefaultLanguageHighlighterColors.METADATA

            WitTypes.UNION_ITEM_NAME,
            WitTypes.ENUM_ITEM_NAME,
            WitTypes.WORLD_NAME,
            WitTypes.RECORD_ITEM_NAME -> DefaultLanguageHighlighterColors.INTERFACE_NAME

            WitTypes.STRING_LITERAL,
            WitTypes.CHAR_LITERAL -> DefaultLanguageHighlighterColors.STRING
            WitTypes.INTEGER_LITERAL,
            WitTypes.DOUBLE_LITERAL -> DefaultLanguageHighlighterColors.NUMBER
            WitTypes.SEMANTIC_VERSION -> DefaultLanguageHighlighterColors.NUMBER

            WitTypes.IDENTIFIER -> DefaultLanguageHighlighterColors.IDENTIFIER

            WitTypes.COMMA -> DefaultLanguageHighlighterColors.COMMA
            WitTypes.LBRACE, WitTypes.RBRACE -> DefaultLanguageHighlighterColors.BRACES
            WitTypes.LPAREN, WitTypes.RPAREN -> DefaultLanguageHighlighterColors.PARENTHESES
            WitTypes.LBRACK, WitTypes.RBRACK -> DefaultLanguageHighlighterColors.BRACKETS
            WitTypes.DOT -> DefaultLanguageHighlighterColors.DOT
            WitTypes.SEMICOLON -> DefaultLanguageHighlighterColors.SEMICOLON

            WitTypes.EQ,
            WitTypes.PLUS,
            WitTypes.MINUS,
            WitTypes.MUL,
            WitTypes.DIV,
            WitTypes.REM,
            WitTypes.OR,
            WitTypes.LT,
            WitTypes.GT,
            WitTypes.COLON,
            WitTypes.ARROW -> DefaultLanguageHighlighterColors.OPERATION_SIGN

            WitTypes.AT,
            WitTypes.GATE_UNSTABLE_WORD,
            WitTypes.GATE_SINCE_WORD,
            WitTypes.GATE_DEPRECATED_WORD -> DefaultLanguageHighlighterColors.METADATA

            else -> null
        }
        return textAttributesKey?.let { arrayOf(it) } ?: emptyArray()
    }
}
