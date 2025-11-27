package com.intellij.webassembly.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.Project
import com.intellij.webassembly.lang.psi.WebAssemblyFileType
import javax.swing.Icon

class WebAssemblyRunConfigurationType : ConfigurationType {
    companion object {
        const val ID = "WebAssemblyRunConfiguration"
    }

    override fun getDisplayName(): String = "WebAssembly"

    override fun getConfigurationTypeDescription(): String =
        "Run WebAssembly (.wat) files by compiling to .wasm and executing main()"

    override fun getIcon(): Icon = WebAssemblyFileType.icon

    override fun getId(): String = ID

    override fun getConfigurationFactories(): Array<ConfigurationFactory> =
        arrayOf(WebAssemblyConfigurationFactory(this))
}

class WebAssemblyConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    override fun getId(): String = "WebAssembly"

    override fun createTemplateConfiguration(project: Project): RunConfiguration =
        WebAssemblyRunConfiguration(project, this, "WebAssembly")

    override fun getName(): String = "WebAssembly"

    override fun getOptionsClass() = WebAssemblyRunConfigurationOptions::class.java
}
