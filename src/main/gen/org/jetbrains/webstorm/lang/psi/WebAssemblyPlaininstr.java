// This is a generated file. Not intended for manual editing.
package org.jetbrains.webstorm.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface WebAssemblyPlaininstr extends PsiElement {

  @Nullable
  WebAssemblyAligneq getAligneq();

  @Nullable
  WebAssemblyArrayGetInstr getArrayGetInstr();

  @Nullable
  WebAssemblyArrayLenInstr getArrayLenInstr();

  @Nullable
  WebAssemblyArrayNewInstr getArrayNewInstr();

  @Nullable
  WebAssemblyArraySetInstr getArraySetInstr();

  @Nullable
  WebAssemblyCallIndirectInstr getCallIndirectInstr();

  @Nullable
  WebAssemblyCallInstr getCallInstr();

  @Nullable
  WebAssemblyElemDropInstr getElemDropInstr();

  @Nullable
  WebAssemblyGlobalInstr getGlobalInstr();

  @Nullable
  WebAssemblyIdx getIdx();

  @Nullable
  WebAssemblyLocalInstr getLocalInstr();

  @Nullable
  WebAssemblyMemoryIdxInstr getMemoryIdxInstr();

  @Nullable
  WebAssemblyOffseteq getOffseteq();

  @Nullable
  WebAssemblyRefFuncInstr getRefFuncInstr();

  @Nullable
  WebAssemblyStructGetInstr getStructGetInstr();

  @Nullable
  WebAssemblyStructNewInstr getStructNewInstr();

  @Nullable
  WebAssemblyStructSetInstr getStructSetInstr();

  @Nullable
  WebAssemblyTableCopyInstr getTableCopyInstr();

  @Nullable
  WebAssemblyTableIdxInstr getTableIdxInstr();

  @Nullable
  WebAssemblyTableInitInstr getTableInitInstr();

}
