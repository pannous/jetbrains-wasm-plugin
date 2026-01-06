package com.intellij.webassembly.lang.editor

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.webassembly.lang.psi.WebAssemblyModulefield
import com.intellij.webassembly.lang.psi.WebAssemblyTypes
import org.junit.Test

class DebugPsiTest : BasePlatformTestCase() {

    @Test
    fun testPsiStructure() {
        myFixture.configureByText("test.wat", """
(module
  (func ${'$'}tee (result i64)
    (i64.const 42)
  )

  (func ${'$'}main (result i32)
    (call ${'$'}tee)
    (i32.wrap_i64)
  )
)
        """.trimIndent())

        val modulefields = PsiTreeUtil.findChildrenOfType(myFixture.file, WebAssemblyModulefield::class.java)

        println("=== PSI Structure Debug ===")
        println("Found ${modulefields.size} modulefields")

        modulefields.forEachIndexed { index, mf ->
            println("\nModulefield[$index]:")
            println("  Element: $mf")
            println("  Type: ${mf.node.elementType}")
            println("  Parent: ${mf.parent} (${mf.parent.node?.elementType})")
            println("  First child: ${mf.firstChild} (${mf.firstChild?.node?.elementType})")

            if (mf.firstChild?.node?.elementType == WebAssemblyTypes.FUNC) {
                val func = mf.firstChild
                val idElement = func?.children?.firstOrNull { it.node?.elementType == WebAssemblyTypes.IDENTIFIER }
                println("  Function name ID: ${idElement?.text}")
            }
        }

        println("\n=== Module children ===")
        val module = modulefields.firstOrNull()?.parent
        module?.children?.forEachIndexed { index, child ->
            println("Child[$index]: $child (${child.node?.elementType}), firstChild: ${child.firstChild?.node?.elementType}")
        }
    }
}
