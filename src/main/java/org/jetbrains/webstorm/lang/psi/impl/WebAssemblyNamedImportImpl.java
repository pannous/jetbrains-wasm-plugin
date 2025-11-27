package org.jetbrains.webstorm.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.webassembly.lang.psi.WebAssemblyElementFactory;
import com.intellij.webassembly.lang.psi.WebAssemblyTypes;
import org.jetbrains.webstorm.lang.psi.WebAssemblyNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WebAssemblyNamedImportImpl extends ASTWrapperPsiElement implements WebAssemblyNamedElement {

    public WebAssemblyNamedImportImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    @Nullable
    public PsiElement getNameIdentifier() {
        ASTNode importdescNode = getNode().findChildByType(WebAssemblyTypes.IMPORTDESC);
        if (importdescNode == null) {
            return null;
        }

        ASTNode identNode = importdescNode.findChildByType(WebAssemblyTypes.IDENTIFIER);
        return identNode != null ? identNode.getPsi() : null;
    }

    @Override
    @Nullable
    public PsiElement setName(@NotNull String name) {
        ASTNode importdescNode = getNode().findChildByType(WebAssemblyTypes.IMPORTDESC);
        if (importdescNode == null) {
            return null;
        }

        ASTNode newImport = WebAssemblyElementFactory.INSTANCE
                .createImport(getNode().getPsi().getProject(), name);
        if (newImport == null) {
            return null;
        }

        ASTNode newImportdesc = newImport.findChildByType(WebAssemblyTypes.IMPORTDESC);
        if (newImportdesc == null) {
            return null;
        }

        ASTNode newIdent = newImportdesc.findChildByType(WebAssemblyTypes.IDENTIFIER);
        if (newIdent == null) {
            return null;
        }

        ASTNode existingIdent = importdescNode.findChildByType(WebAssemblyTypes.IDENTIFIER);
        if (existingIdent != null) {
            getNode().replaceChild(existingIdent, newIdent);
            return getNode().getPsi();
        }

        ASTNode anchorNode = importdescNode.findChildByType(WebAssemblyTypes.FUNCKEY);
        if (anchorNode == null) {
            anchorNode = importdescNode.findChildByType(WebAssemblyTypes.MEMORYKEY);
        }
        if (anchorNode == null) {
            anchorNode = importdescNode.findChildByType(WebAssemblyTypes.TABLEKEY);
        }
        if (anchorNode == null) {
            anchorNode = importdescNode.findChildByType(WebAssemblyTypes.GLOBALKEY);
        }

        if (anchorNode != null && anchorNode.getTreeNext() != null) {
            getNode().addChild(newIdent, anchorNode.getTreeNext());
        }

        return getNode().getPsi();
    }
}
