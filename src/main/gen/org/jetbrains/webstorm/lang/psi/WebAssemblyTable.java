// This is a generated file. Not intended for manual editing.
package org.jetbrains.webstorm.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface WebAssemblyTable extends WebAssemblyNamedElement {

  @Nullable
  WebAssemblyInlineElem getInlineElem();

  @NotNull
  List<WebAssemblyInlineExport> getInlineExportList();

  @Nullable
  WebAssemblyInlineImport getInlineImport();

  @Nullable
  WebAssemblyTabletype getTabletype();

  //WARNING: setName(...) is skipped
  //matching setName(WebAssemblyTable, ...)
  //methods are not found in null

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(WebAssemblyTable, ...)
  //methods are not found in null

}
