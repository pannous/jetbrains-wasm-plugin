// This is a generated file. Not intended for manual editing.
package org.jetbrains.webstorm.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface WebAssemblyGlobal extends WebAssemblyNamedElement {

  @Nullable
  WebAssemblyGlobaltype getGlobaltype();

  @NotNull
  List<WebAssemblyInlineExport> getInlineExportList();

  @Nullable
  WebAssemblyInlineImport getInlineImport();

  @NotNull
  List<WebAssemblyInstr> getInstrList();

  //WARNING: setName(...) is skipped
  //matching setName(WebAssemblyGlobal, ...)
  //methods are not found in null

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(WebAssemblyGlobal, ...)
  //methods are not found in null

}
