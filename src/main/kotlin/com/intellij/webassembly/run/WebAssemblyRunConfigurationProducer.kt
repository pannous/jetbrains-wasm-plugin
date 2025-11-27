package com.intellij.webassembly.run

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.webassembly.lang.psi.WebAssemblyFile

class WebAssemblyRunConfigurationProducer : LazyRunConfigurationProducer<WebAssemblyRunConfiguration>() {

    override fun getConfigurationFactory(): ConfigurationFactory {
        val configurationType = ConfigurationType.CONFIGURATION_TYPE_EP.findExtension(WebAssemblyRunConfigurationType::class.java)
            ?: error("WebAssemblyRunConfigurationType not registered")
        return configurationType.configurationFactories[0]
    }

    override fun isConfigurationFromContext(
        configuration: WebAssemblyRunConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val file = context.location?.psiElement?.containingFile ?: return false
        if (file !is WebAssemblyFile) return false

        val virtualFile = file.virtualFile ?: return false
        return configuration.watFilePath == virtualFile.path
    }

    override fun setupConfigurationFromContext(
        configuration: WebAssemblyRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val file = context.location?.psiElement?.containingFile ?: return false
        if (file !is WebAssemblyFile) return false

        val virtualFile = file.virtualFile ?: return false
        val extension = virtualFile.extension

        // Only support .wat and .wast files
        if (extension != "wat" && extension != "wast") return false

        configuration.watFilePath = virtualFile.path
        configuration.name = "Run ${virtualFile.name}"

        return true
    }
}
