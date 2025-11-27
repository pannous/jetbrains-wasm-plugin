package com.intellij.webassembly.run

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class WebAssemblySettingsEditor(private val project: Project) : SettingsEditor<WebAssemblyRunConfiguration>() {
    private val watFileField = TextFieldWithBrowseButton()

    init {
        val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
            .withFileFilter { it.extension == "wat" || it.extension == "wast" }
            .withTitle("Select WebAssembly Text File")
        watFileField.addBrowseFolderListener(
            "Select .wat File",
            "Choose the WebAssembly text file to run",
            project,
            descriptor
        )
    }

    override fun createEditor(): JComponent {
        return FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("WAT file:"), watFileField, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    override fun resetEditorFrom(configuration: WebAssemblyRunConfiguration) {
        watFileField.text = configuration.watFilePath
    }

    override fun applyEditorTo(configuration: WebAssemblyRunConfiguration) {
        configuration.watFilePath = watFileField.text
    }
}
