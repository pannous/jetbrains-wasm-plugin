package com.intellij.webassembly.run

import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.jdom.Element

class WebAssemblyRunConfiguration(
    project: Project,
    factory: ConfigurationFactory,
    name: String
) : RunConfigurationBase<WebAssemblyRunConfigurationOptions>(project, factory, name) {

    var watFilePath: String = ""

    override fun getOptions(): WebAssemblyRunConfigurationOptions {
        return super.getOptions() as WebAssemblyRunConfigurationOptions
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        return WebAssemblySettingsEditor(project)
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return WebAssemblyRunState(environment, this)
    }

    override fun readExternal(element: Element) {
        super.readExternal(element)
        watFilePath = element.getAttributeValue("watFilePath") ?: ""
    }

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        element.setAttribute("watFilePath", watFilePath)
    }
}

class WebAssemblyRunConfigurationOptions : RunConfigurationOptions()
