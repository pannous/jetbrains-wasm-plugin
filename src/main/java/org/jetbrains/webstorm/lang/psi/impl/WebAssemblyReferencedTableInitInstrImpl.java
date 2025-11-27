package org.jetbrains.webstorm.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.webassembly.lang.WebAssemblyReference;
import com.intellij.webassembly.lang.WebAssemblyUtil;
import com.intellij.webassembly.lang.psi.WebAssemblyTypes;
import org.jetbrains.webstorm.lang.psi.WebAssemblyModulefield;
import org.jetbrains.webstorm.lang.psi.WebAssemblyReferencedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WebAssemblyReferencedTableInitInstrImpl extends ASTWrapperPsiElement implements WebAssemblyReferencedElement {

    public WebAssemblyReferencedTableInitInstrImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        List<PsiReference> result = new ArrayList<>();

        ASTNode[] children = getNode().getChildren(TokenSet.create(WebAssemblyTypes.IDX));

        WebAssemblyModulefield parent = PsiTreeUtil.getParentOfType(getNode().getPsi(), WebAssemblyModulefield.class);
        if (parent != null) {
            switch (children.length) {
                case 1:
                    result.add(new WebAssemblyReference(getNode(), children[0],
                        WebAssemblyUtil.INSTANCE.findModulefield(WebAssemblyTypes.ELEM, parent)));
                    break;
                case 2:
                    result.add(new WebAssemblyReference(getNode(), children[0],
                        WebAssemblyUtil.INSTANCE.findImportedModulefield(WebAssemblyTypes.TABLE, parent)));
                    result.add(new WebAssemblyReference(getNode(), children[1],
                        WebAssemblyUtil.INSTANCE.findModulefield(WebAssemblyTypes.ELEM, parent)));
                    break;
                default:
                    break;
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
}
