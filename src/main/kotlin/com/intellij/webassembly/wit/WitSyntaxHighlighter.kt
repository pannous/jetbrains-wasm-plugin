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

            WitTypes.STRING_LITERAL -> DefaultLanguageHighlighterColors.STRING
            WitTypes.INTEGER_LITERAL,
            WitTypes.DOUBLE_LITERAL -> DefaultLanguageHighlighterColors.NUMBER

            WitTypes.COMMA -> DefaultLanguageHighlighterColors.COMMA
            WitTypes.LBRACE, WitTypes.RBRACE -> DefaultLanguageHighlighterColors.BRACES
            WitTypes.LPAREN, WitTypes.RPAREN -> DefaultLanguageHighlighterColors.PARENTHESES
            WitTypes.DOT -> DefaultLanguageHighlighterColors.DOT

            else -> null
        }
        return textAttributesKey?.let { arrayOf(it) } ?: emptyArray()
    }
}
