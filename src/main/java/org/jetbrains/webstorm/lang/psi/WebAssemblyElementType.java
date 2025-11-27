package org.jetbrains.webstorm.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.webassembly.lang.WebAssemblyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class WebAssemblyElementType extends IElementType {
    public WebAssemblyElementType(@NotNull @NonNls String debugName) {
        super(debugName, WebAssemblyLanguage.INSTANCE);
    }
}
