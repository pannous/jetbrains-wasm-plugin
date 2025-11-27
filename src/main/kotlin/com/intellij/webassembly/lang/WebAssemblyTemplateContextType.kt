package com.intellij.webassembly.lang

import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType

class WebAssemblyTemplateContextType : TemplateContextType("WebAssembly", "WebAssembly") {
  override fun isInContext(templateActionContext: TemplateActionContext): Boolean {
    return templateActionContext.file.name.endsWith(".wat") ||
           templateActionContext.file.name.endsWith(".wast")
  }
}
