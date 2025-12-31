package com.intellij.webassembly.lang.editor

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test

class WebAssemblyGotoDefinitionTest : BasePlatformTestCase() {

    @Test
    fun testFunctionCallGoToDefinition() {
        myFixture.configureByText("test.wat", """
(module
  (func ${'$'}tee (result i64)
    (i64.const 42)
  )

  (func ${'$'}main (result i32)
    (call <caret>${'$'}tee)
    (i32.wrap_i64)
  )
)
        """.trimIndent())

        val element = myFixture.file.findElementAt(myFixture.caretOffset)
        val idx = element?.parent // Should be IDX
        val callInstr = idx?.parent // Should be CALL_INSTR

        // Get reference and try to resolve
        val reference = callInstr?.reference
        val target = reference?.resolve()

        // Debug output if resolution fails
        if (target == null) {
            val debugInfo = buildString {
                appendLine("=== DEBUG: Reference Resolution Failed ===")
                appendLine("Element: $element (${element?.node?.elementType})")
                appendLine("IDX: $idx (${idx?.node?.elementType})")
                appendLine("Call Instr: $callInstr (${callInstr?.node?.elementType})")
                appendLine("Call Instr class: ${callInstr?.javaClass}")
                appendLine("References count: ${callInstr?.references?.size}")
                callInstr?.references?.forEach { ref ->
                    appendLine("  Reference: $ref")
                    appendLine("    Class: ${ref.javaClass}")
                    appendLine("    Element: ${ref.element}")
                    appendLine("    Canonical: ${ref.canonicalText}")
                }
            }
            fail(debugInfo)
        }

        assertNotNull("Reference should resolve to function definition", target)
        assertTrue("Should resolve to function identifier", target?.text?.contains("${'$'}tee") == true)
    }
}
