package com.intellij.webassembly.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.webassembly.lang.WebAssemblyLanguage;
import com.intellij.webassembly.lang.lexer.WebAssemblyLexer;
import com.intellij.webassembly.lang.psi.WebAssemblyFile;
import com.intellij.webassembly.lang.psi.WebAssemblyTypes;
import org.jetbrains.annotations.NotNull;

public class WebAssemblyParserDefinition implements ParserDefinition {

    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(WebAssemblyTypes.LINE_COMMENT, WebAssemblyTypes.BLOCK_COMMENT);

    private static final IFileElementType FILE = new IFileElementType(WebAssemblyLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new WebAssemblyLexer();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(Project project) {
        return new WebAssemblyParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new WebAssemblyFile(viewProvider);
    }

    @NotNull
    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return WebAssemblyTypes.Factory.createElement(node);
    }
}
