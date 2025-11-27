// This is a generated file. Not intended for manual editing.
package org.jetbrains.webstorm.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface WebAssemblyFunc extends WebAssemblyNamedElement {

  @NotNull
  List<WebAssemblyInlineExport> getInlineExportList();

  @Nullable
  WebAssemblyInlineImport getInlineImport();

  @NotNull
  List<WebAssemblyInstr> getInstrList();

  @NotNull
  List<WebAssemblyLocal> getLocalList();

  @Nullable
  WebAssemblyTypeuse getTypeuse();

  //WARNING: setName(...) is skipped
  //matching setName(WebAssemblyFunc, ...)
  //methods are not found in null

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(WebAssemblyFunc, ...)
  //methods are not found in null

}
