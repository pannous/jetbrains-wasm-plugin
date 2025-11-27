package org.jetbrains.webstorm.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.TokenSet;
import com.intellij.webassembly.lang.WebAssemblyReference;
import com.intellij.webassembly.lang.psi.WebAssemblyElementFactory;
import com.intellij.webassembly.lang.psi.WebAssemblyTypes;
import org.jetbrains.webstorm.lang.psi.WebAssemblyNamedReferencedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WebAssemblyNamedReferencedElementImpl extends ASTWrapperPsiElement implements WebAssemblyNamedReferencedElement {

    public WebAssemblyNamedReferencedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        List<PsiReference> result = new ArrayList<>();

        ASTNode targetNode;
        if (getNode().getElementType() == WebAssemblyTypes.EXPORT) {
            targetNode = getNode().findChildByType(WebAssemblyTypes.EXPORTDESC);
        } else {
            targetNode = getNode();
        }

        if (targetNode != null) {
            ASTNode[] children = targetNode.getChildren(TokenSet.create(WebAssemblyTypes.IDX));
            for (ASTNode child : children) {
                result.add(new WebAssemblyReference(getNode(), child));
            }
        }

        return result.toArray(new PsiReference[0]);
    }

    @Override
    @Nullable
    public PsiReference getReference() {
        PsiReference[] refs = getReferences();
        return refs.length > 0 ? refs[0] : null;
    }

    @Override
    @Nullable
    public PsiElement getNameIdentifier() {
        ASTNode identNode = getNode().findChildByType(WebAssemblyTypes.IDENTIFIER);
        return identNode != null ? identNode.getPsi() : null;
    }

    @Override
    @Nullable
    public PsiElement setName(@NotNull String name) {
        ASTNode newIdent = WebAssemblyElementFactory.INSTANCE
                .createElement(getNode().getPsi().getProject(), name)
                .findChildByType(WebAssemblyTypes.IDENTIFIER);

        if (newIdent == null) {
            return null;
        }

        ASTNode existingIdent = getNode().findChildByType(WebAssemblyTypes.IDENTIFIER);
        if (existingIdent != null) {
            getNode().replaceChild(existingIdent, newIdent);
            return getNode().getPsi();
        }

        getNode().addChild(newIdent, getNode().getFirstChildNode().getTreeNext().getTreeNext());
        return getNode().getPsi();
    }
}
