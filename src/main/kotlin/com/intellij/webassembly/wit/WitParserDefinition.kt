package com.intellij.webassembly.wit

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class WitParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = WitLexerAdapter()

    override fun createParser(project: Project?): PsiParser {
        throw UnsupportedOperationException("Parser not implemented for WIT (syntax highlighting only)")
    }

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode?): PsiElement {
        throw UnsupportedOperationException("createElement not implemented")
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile = WitFile(viewProvider)

    companion object {
        val FILE = IFileElementType(WitLanguage)
    }
}
